package com.example.program.ui.home.sub

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivityExcerciseTypeBinding
import com.example.program.ui.dialog.CancelDialog
import com.example.program.ui.dialog.UpdateDialog
import com.example.program.util.DateUtil
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExerciseTypeActivity :
    BaseActivity<ActivityExcerciseTypeBinding>(R.layout.activity_excercise_type) {

    private val viewModel: RegExerciseTypeViewModel by viewModels()

    private var selectedSplitIndex: Int? = 0

    private var exercisesSize = 0

    private var splitCount: Int? = null
    private var splitText: String? = null
    private var programNo: Long = 0L
    private var isIntentToExercise = false

    private lateinit var cancelDialog: CancelDialog
    private lateinit var registerDialog: UpdateDialog

    private lateinit var exerciseTypeAdapter: ExerciseTypeAdapter

    private var performedExerciseCount = 0

    // 운동 종류 등록을 마친 후,
    private val onResultForExerciseReg = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        if (result?.resultCode == Activity.RESULT_OK) {
            viewModel.getExercises(
                programNo = programNo,
                splitIndex = selectedSplitIndex
            ) { exercises ->
                exercisesSize = exercises.size
                if (exercisesSize > 0)
                    dataBinding.tvRegSuccess.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.black))
            }
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

        splitCount = intent.getIntExtra("splitCount", -1)
        splitText = intent.getStringExtra("splitText")
        programNo = intent.getLongExtra("programNo", 0L)
        isIntentToExercise = intent.getBooleanExtra("isIntentToExercise", false)
        if (intent.getBooleanExtra("isIntentToExercise", false)) {
            dataBinding.tvRegSuccess.isVisible = false
            dataBinding.tvExerciseStart.isVisible = true
        }

        binding {
            regVm = viewModel

            tlSplit.apply {
                for (i in 0 until splitCount!!) {
                    tlSplit.addTab(tlSplit.newTab().setText("${i + 1}" + "분할"))
                }

                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabUnselected(tab: TabLayout.Tab?) {}
                    override fun onTabReselected(tab: TabLayout.Tab?) {}
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        selectedSplitIndex = tab?.position

                        viewModel.getExercises(
                            programNo = programNo,
                            splitIndex = selectedSplitIndex
                        )
                    }
                })
            }

            layoutAddExerciseType.setOnClickListener {
                Intent(this@ExerciseTypeActivity, RegExerciseTypeDetailActivity::class.java).apply {
                    putExtra("selectedSplitIndex", selectedSplitIndex)
                    putExtra("splitText", splitText)
                    putExtra("programNo", programNo)
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
                        // 우측 메뉴 버튼 클릭
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
                                        putExtra("selectedSplitIndex", selectedSplitIndex)
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
                                goMain()
                            }
                        registerDialog.show(
                            supportFragmentManager,
                            registerDialog.tag
                        )
                    }
                    false -> showToast("운동 종류를 등록해 주세요!")
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

    }

    private fun initPerformedExercises() {
        viewModel.getExercises(
            programNo = programNo,
            splitIndex = selectedSplitIndex
        ) { exercises ->
            // 운동 기록을 마치고 돌아올 때,
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
        }
    }

    override fun onBackPressed() {
        if (!isIntentToExercise) {
            cancelDialog = CancelDialog.newInstance(
                { // 저장 안함
                    viewModel.deleteProgram(
                        programNo
                    ) {
                        super.onBackPressed()
                    }
                },
                { // 저장
                    super.onBackPressed()
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
                super.onBackPressed()
            }
        }
    }

}