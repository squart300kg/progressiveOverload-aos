package com.example.program.ui.home.sub

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivityExcerciseTypeBinding
import com.example.program.ui.dialog.CancelDialog
import com.example.program.ui.dialog.UpdateDialog
import com.example.program.util.Ad.AdUtil
import com.example.program.util.Ad.FullScreenAdCallback
import com.example.program.util.DateUtil
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.material.tabs.TabLayout
import com.securepreferences.SecurePreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ExerciseTypeActivity :
    BaseActivity<ActivityExcerciseTypeBinding>(R.layout.activity_excercise_type),
    FullScreenAdCallback {

    private val viewModel: RegExerciseTypeViewModel by viewModels()

    @Inject
    lateinit var securePreferences: SecurePreferences

    private var mesoCycleSplitIndex = 0
    private var microCycleSplitIndex = 0

    private var exercisesSize = 0

    private var mesoCycleSplitCount: Int = 0
    private var mesoCycleSplitText: String = ""
    private var microCycleCount: Int = 0
    private var microCycleText: String = ""

    private var programNo: Long = 0L
    private var isIntentToExercise = false

    private lateinit var cancelDialog: CancelDialog
    private lateinit var registerDialog: UpdateDialog

    private lateinit var exerciseTypeAdapter: ExerciseTypeAdapter

    private var performedExerciseCount = 0

    private var mInterstitialAd: InterstitialAd? = null

    private var intentAfterFullScreenAd = -1

    // 운동 종류 등록을 마친 후,
    private val onResultForExerciseReg = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        if (result?.resultCode == Activity.RESULT_OK) {
            initPerformedExercises()
        }
    }

    // 운동 기록을 마친 후,
    private val onResultForExerciseRecord = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        if (result?.resultCode == Activity.RESULT_OK) {
            initPerformedExercises()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mesoCycleSplitCount = intent.getIntExtra("mesoCycleSplitCount", 0)
        mesoCycleSplitText = intent.getStringExtra("mesoCycleSplitText").toString()
        microCycleCount = intent.getIntExtra("microCycleSplitCount", 0)
        microCycleText = intent.getStringExtra("microCycleSplitText").toString()

        programNo = intent.getLongExtra("programNo", 0L)
        isIntentToExercise = intent.getBooleanExtra("isIntentToExercise", false)
        if (intent.getBooleanExtra("isIntentToExercise", false)) {
            dataBinding.tvRegSuccess.isVisible = false
            dataBinding.layoutGuide.isVisible = false
            dataBinding.tvExerciseStart.isVisible = true
        }

        binding {
            regVm = viewModel

            tlMesoSplit.apply {
                for (i in 0 until mesoCycleSplitCount) {
                    tlMesoSplit.addTab(tlMesoSplit.newTab().setText("${i + 1}" + "주차"))
                }

                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabUnselected(tab: TabLayout.Tab?) {}
                    override fun onTabReselected(tab: TabLayout.Tab?) {}
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        tab?.position?.let {
                            mesoCycleSplitIndex = it
                            initPerformedExercises()
                        }
                    }
                })
            }

            tlMicroSplit.apply {
                for (i in 0 until microCycleCount) {
                    tlMicroSplit.addTab(tlMicroSplit.newTab().setText("${i + 1}" + "일차"))
                }

                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabUnselected(tab: TabLayout.Tab?) {}
                    override fun onTabReselected(tab: TabLayout.Tab?) {}
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        tab?.position?.let {
                            microCycleSplitIndex = it
                            initPerformedExercises()
                        }
                    }
                })
            }

            // 운동 종류 등록
            layoutAddExerciseType.setOnClickListener {
                Intent(this@ExerciseTypeActivity, RegExerciseTypeDetailActivity::class.java).apply {
                    putExtra("programNo", programNo)
                    putExtra("mesoCycleSplitIndex", mesoCycleSplitIndex)
                    putExtra("microCycleSplitIndex", microCycleSplitIndex)
                    onResultForExerciseReg.launch(this)
                }
            }

            rvExercises.apply {
                setHasFixedSize(true)
                exerciseTypeAdapter = ExerciseTypeAdapter(this@ExerciseTypeActivity,
                    {
                        // 운동 기록 시작
                        Intent(this@ExerciseTypeActivity,
                            RecordExerciseActivity::class.java).apply {
                            putExtra("exTypeModel", it)
                            putExtra("targetedDate", DateUtil.getCurrentDateForRecord())
                            onResultForExerciseRecord.launch(this)
                        }
                    },
                    { exercise, position ->
                        // 우측 메뉴 버튼 클릭, 다이얼로그
                        val builder =
                            AlertDialog.Builder(ContextThemeWrapper(this@ExerciseTypeActivity,
                                R.style.AlertDialogCustom))
                        builder.setItems(R.array.exercise_sort_array
                        ) { dialog, pos ->
                            when (pos) {
                                0 -> { // 프로그램 수정
                                    Intent(this@ExerciseTypeActivity,
                                        RegExerciseTypeDetailActivity::class.java).apply {
                                        putExtra("isUpdate", true)
                                        putExtra("mesoCycleSplitIndex", mesoCycleSplitIndex)
                                        putExtra("microCycleSplitIndex", microCycleSplitIndex)
                                        putExtra("exTypeModel", exercise)
                                        putExtra("programNo", programNo)
                                        onResultForExerciseReg.launch(this)
                                    }
                                }
                                1 -> { // 프로그램 삭제
                                    val errorDialog = AlertDialog.Builder(ContextThemeWrapper(
                                        context,
                                        R.style.AlertDialogCustom))
                                        .setCancelable(true)
                                        .setMessage("정말 삭제하시겠습니까?\n\n해당 운동을 삭제하면 기록도 모두 삭제됩니다.")
                                        .setPositiveButton("삭제") { dialog, _ ->
                                            viewModel.deleteExercise(exercise) {
                                                dialog?.dismiss()
                                            }
                                            exerciseTypeAdapter.removeTargetedItem(position)

                                        }
                                        .setNegativeButton("취소") { _, _ -> }
                                        .create()
                                    errorDialog.show()
                                }
                            }
                            dialog?.dismiss()
                        }
                        val dialog = builder.create()
                        dialog.show()
                    }
                )
                adapter = exerciseTypeAdapter
            }

            tvRegSuccess.setOnClickListener {
                when (exercisesSize > 0) {
                    true -> {
                        registerDialog =
                            UpdateDialog.newInstance(programNo) {

                                // 광고 내려간 직후, 메인화면으로 이동시킴
                                intentAfterFullScreenAd = GO_MAIN
                                startFullScreenAd()

                            }
                        registerDialog.show(
                            supportFragmentManager,
                            registerDialog.tag
                        )
                    }
                    false -> showToast("운동을 등록해 주세요!")
                }
            }

            tvExerciseStart.setOnClickListener {
                exerciseTypeAdapter.initExerciseStatus(ExerciseTypeAdapter.START) {
                    layoutAddExerciseType.isVisible = false
                    tvExerciseStart.isVisible = false
                    tvBtn.isVisible = false
                    showToast("오늘 운동 시작!")
                }
            }
        }

        initPerformedExercises()

        initBannerAd(dataBinding.adView)

        initFullScreenAd(this)
    }

    private fun initPerformedExercises() {
        viewModel.getExercises(
            programNo = programNo,
            mesoCycleSplitIndex = mesoCycleSplitIndex,
            microCycleSplitIndex = microCycleSplitIndex,
        ) { exercises ->

            // 운동 기록을 마치고 돌아올 때, 수행한 운동 완료표시 해준다
            if (isIntentToExercise) {

                val performedExerciseIndexes = mutableListOf<Int>()
                for (index in exercises.indices) {
                    viewModel.getPerformedSets(
                        programNo = exercises[index].programNo,
                        exerciseNo = exercises[index].no
                    ) { sets ->
                        Log.i("statuses", "==== ${exercises[index].no}번째 ex ===")
                        Log.i("statuses", "total set : ${exercises[index].setNum!!}")
                        Log.i("statuses", "performed set : $sets")

                        performedExerciseCount++

                        // 모든 세트를 마쳤다면?
                        if (viewModel.exercises.value?.get(index)?.setNum!! <= sets) {
                            Log.i("statuses", "success Exercise!!, - $index")
                            performedExerciseIndexes.add(index)
                        }

                        // 마지막일 때,
                        if (performedExerciseCount == exercises.size) {
                            performedExerciseIndexes.sortBy { it }
                            Log.i("statuses",
                                "viewModel들어가기 직전, indexe : $index, exSize : ${exercises.size}, count ; $performedExerciseCount")
                            Log.i("statuses",
                                "viewModel들어가기 직전, indexes : $performedExerciseIndexes")
                            viewModel.initPerformedExerciesSetTrue(performedExerciseIndexes) {
                                performedExerciseCount = 0
                            }
                        }
                    }
                }
            }

            // '등록 완료'버튼 활성화
            exercisesSize = exercises.size
            if (exercisesSize > 0)
                dataBinding.tvRegSuccess.isSelected = true
        }
    }

    override fun onBackPressed() {
        if (!isIntentToExercise) {

            // 프로그램을 저장할 것인가?
            cancelDialog = CancelDialog.newInstance(
                { // 저장 안함
                    viewModel.deleteProgram(
                        programNo
                    ) {
                        super.onBackPressed()
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                    }
                },
                { // 저장

                    if (AdUtil.isTurnToExposeAd(securePreferences)) {
                        // 광고 내려간 직후, 메인화면으로 이동시킴
                        intentAfterFullScreenAd = GO_MAIN
                        startFullScreenAd()
                    } else {
                        goMain()
                    }


                }
            )
            cancelDialog.show(
                supportFragmentManager,
                cancelDialog.tag
            )
        } else {

            // 만약, 운동을 시작한 상태라면
            if (!dataBinding.layoutAddExerciseType.isVisible &&
                !dataBinding.tvExerciseStart.isVisible &&
                !dataBinding.tvBtn.isVisible) {
                exerciseTypeAdapter.initExerciseStatus(ExerciseTypeAdapter.STOP) {
                    dataBinding.layoutAddExerciseType.isVisible = true
                    dataBinding.tvExerciseStart.isVisible = true
                    dataBinding.tvBtn.isVisible = true
                }
            } else {

                if (AdUtil.isTurnToExposeAd(securePreferences)) {
                    // 광고 내려간 직후, 뒤로가기시킴
                    intentAfterFullScreenAd = GO_BACK
                    startFullScreenAd()
                } else {
                    finish()
                }
            }
        }
    }

    companion object {
        const val GO_MAIN = 0
        const val GO_BACK = 1
    }

    override fun onCloseFullScreenAd() {
        when (intentAfterFullScreenAd) {
            GO_MAIN -> goMain()
            GO_BACK-> finish()
        }
    }

}