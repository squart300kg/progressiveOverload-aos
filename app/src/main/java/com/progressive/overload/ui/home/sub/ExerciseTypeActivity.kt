package com.progressive.overload.ui.home.sub

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.google.android.material.tabs.TabLayout
import com.progressive.overload.R
import com.progressive.overload.base.BaseActivity
import com.progressive.overload.databinding.ActivityExcerciseTypeBinding
import com.progressive.overload.ui.dialog.CancelDialog
import com.progressive.overload.ui.dialog.Input1RMDialog
import com.progressive.overload.ui.dialog.TitleDialog
import com.progressive.overload.ui.home.TutorialExerciseTypeActivity
import com.progressive.overload.util.Ad.AdUtil
import com.progressive.overload.util.Ad.FullScreenAdCallback
import com.progressive.overload.util.DateUtil
import com.progressive.overload.util.GuideUtil
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

    private var isDummyDataInit = false
    private var isDummy = false
    private var mesoCycleSplitCount: Int = 0
    private var mesoCycleSplitText: String = ""
    private var microCycleCount: Int = 0
    private var microCycleText: String = ""

    private var programNo: Long = 0L
    private var isIntentToExercise = false

    private lateinit var cancelDialog: CancelDialog
    private lateinit var titleDialog: TitleDialog
    private lateinit var input1RMDialog: Input1RMDialog

    private lateinit var exerciseTypeAdapter: ExerciseTypeAdapter

    private var performedExerciseCount = 0

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

        // 가이드 테스트
        GuideUtil.saveReadyGuideShown(securePreferences, false)
        GuideUtil.saveStartGuideShown(securePreferences, false)

        isDummyDataInit = intent.getBooleanExtra("isDummyDataInit", false)
        isDummy = intent.getBooleanExtra("isDummy", false)
        mesoCycleSplitCount = intent.getIntExtra("mesoCycleSplitCount", 0)
        mesoCycleSplitText = intent.getStringExtra("mesoCycleSplitText").toString()
        microCycleCount = intent.getIntExtra("microCycleSplitCount", 0)
        microCycleText = intent.getStringExtra("microCycleSplitText").toString()

        programNo = intent.getLongExtra("programNo", 0L)
        isIntentToExercise = intent.getBooleanExtra("isIntentToExercise", false)

        // 기존에 등록된 거인화 프로그램일 경우 + 중량 초기화가 안된 경우
        if (isDummy && !isDummyDataInit) {
            input1RMDialog =
                Input1RMDialog.newInstance(
                    { // click cancel
                        finish()
                    },
                    { squart1RM, dead1RM, bench1RM, milp1RM -> // click ok
                        if (squart1RM.isNullOrEmpty() || dead1RM.isNullOrEmpty() || bench1RM.isNullOrEmpty() || milp1RM.isNullOrEmpty()) {
                            Toast.makeText(this, "1RM을 빠짐없이 입력해 주세요!", Toast.LENGTH_LONG).show()
                        } else {
                            viewModel.initHyukProgramWeight(
                                programNo,
                                squart1RM,
                                dead1RM,
                                bench1RM,
                                milp1RM
                            ) { // 무게 설정 완료
                                input1RMDialog.dismiss()
                                viewModel.getExercises(programNo, mesoCycleSplitIndex, microCycleSplitIndex)

                                showReadyTutorialAtFirst()
                            }
                        }
                    }
                )
            input1RMDialog.show(supportFragmentManager, input1RMDialog.tag)
        }

        if (isIntentToExercise) {
            dataBinding.tvRegSuccess.isVisible = false
            dataBinding.layoutGuide.isVisible = false
            dataBinding.tvExerciseStart.isVisible = true

            showReadyTutorialAtFirst()
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
                exerciseTypeAdapter = ExerciseTypeAdapter(this@ExerciseTypeActivity, isDummy,
                    {
                        // 운동 기록 시작
                        Intent(this@ExerciseTypeActivity,
                            RecordExerciseActivity::class.java).apply {
                            putExtra("exTypeModel", it)
                            putExtra("targetedDate", DateUtil.getCurrentDateForRecord())
                            onResultForExerciseRecord.launch(this)
                        }
                    },
                    { exercise ->
                        // 우측 메뉴 버튼 클릭, 다이얼로그
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
                )
                adapter = exerciseTypeAdapter
            }

            tvRegSuccess.setOnClickListener {
                when (exercisesSize > 0) {
                    true -> {
                        titleDialog =
                            TitleDialog.newInstance(
                                TitleDialog.INTENT_TO_UPDATE,
                                programNo) {

                                // 광고 내려간 직후, 메인화면으로 이동시킴
                                goMain()
                                startFullScreenAd()

                            }
                        titleDialog.show(
                            supportFragmentManager,
                            titleDialog.tag
                        )
                    }
                    false -> showToast("운동을 등록해 주세요!")
                }
            }

            layoutStartGuide.root.setOnClickListener {
                GuideUtil.saveStartGuideShown(securePreferences, true)
                layoutStartGuide.root.isVisible = false
            }

            tvExerciseStart.setOnClickListener {

                if (!GuideUtil.isStartGuideShown(securePreferences)) {
                    layoutStartGuide.root.isVisible = true
                }

                exerciseTypeAdapter.initExerciseStatus(ExerciseTypeAdapter.START) {
                    layoutAddExerciseType.isVisible = false
                    tvExerciseStart.isVisible = false
                    tvBtn.isVisible = false
                    showToast("운동 준비!")
                }
            }
        }

        initPerformedExercises()

        initBannerAd(dataBinding.adView)

        initFullScreenAd(this)
    }

    private fun showReadyTutorialAtFirst() {
        if (!GuideUtil.isReadyGuideShown(securePreferences)) {
            Intent(this, TutorialExerciseTypeActivity::class.java).apply {
                startActivity(this)
                GuideUtil.saveReadyGuideShown(securePreferences, true)
            }
        }
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
                        goMain()
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
                    finish()
                    startFullScreenAd()
                } else {
                    finish()
                }
            }
        }
    }

    override fun onCloseFullScreenAd() { }

    override fun showInternetDisconnectedView(disconnected: Boolean) {
        dataBinding.viewNetworkNotConnected.root.isVisible = disconnected
    }

}