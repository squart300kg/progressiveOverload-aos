package com.example.program.ui.home.sub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivityRecordExerciseBinding
import com.example.program.model.entity.RecordTable
import com.example.program.model.model.ExerciseTypeModel
import com.example.program.util.DateUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecordExerciseActivity :
    BaseActivity<ActivityRecordExerciseBinding>(R.layout.activity_record_exercise) {

    private val recordExerciseViewModel: RecordExerciseViewModel by viewModel()

    private lateinit var recordExerciseAdapter: RecordExerciseAdapter

    private lateinit var exerciseModel: ExerciseTypeModel
    private var targetedDate: String? = null

    private var isPerformedExerciseAtLeastOneSet = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exerciseModel = intent.getSerializableExtra("exTypeModel") as ExerciseTypeModel
        targetedDate = intent.getStringExtra("targetedDate")

        Log.i("getTargetDate", "onCreate : $targetedDate")


        binding {
            recordExVm = recordExerciseViewModel

            tvGoPrevious.setOnClickListener {
                goPrevious()
            }

            tvGoNext.setOnClickListener {
                goNext()
            }

            tvDate.text = targetedDate

            rvRecordEx.apply {
                setHasFixedSize(true)
                recordExerciseAdapter =
                    RecordExerciseAdapter(this@RecordExerciseActivity, exerciseModel.name
                    ) { model ->
                        recordExerciseViewModel.record(
                            RecordTable(
                                name = exerciseModel.name,
                                weight = model.weight,
                                repitition = model.repitition,
                                setNum = model.no,
                                restTime = model.restTime,
                                rpe = model.rpe,
                                recordTime = DateUtil.getCurrentDateForRecord(),
                                programNo = exerciseModel.programNo,
                                exerciseTypeNo = exerciseModel.no
                            )
                        ) {
                            Log.i("record", "${exerciseModel.name} success")
                            recordExerciseAdapter.successExercise()
                            isPerformedExerciseAtLeastOneSet = true
                        }
                    }
                adapter = recordExerciseAdapter

            }
        }

        recordExerciseViewModel.getTargetedExercisePerformed(
            exerciseModel.programNo,
            exerciseModel.no,
            targetedDate
        ) { recordTable ->
            recordExerciseViewModel.initExercise(exerciseModel, recordTable)
        }

        recordExerciseViewModel.getTargetedAllDate(exerciseModel.programNo, exerciseModel.no)

        recordExerciseViewModel.getPreviousDate(
            exerciseModel.programNo,
            exerciseModel.no,
            targetedDate
        ) { previousDate ->
            if (previousDate == null)
                dataBinding.tvGoPrevious.isVisible = false
            else
                dataBinding.tvGoPrevious.isVisible = true
        }

        recordExerciseViewModel.getNextDate(
            exerciseModel.programNo,
            exerciseModel.no,
            targetedDate
        ) { nextDate ->
            if (nextDate == null && targetedDate == DateUtil.getCurrentDateForRecord())
                dataBinding.tvGoNext.isVisible = false
            else
                dataBinding.tvGoNext.isVisible = true
        }
    }

    private fun goNext() {
        recordExerciseViewModel.getNextDate(
            exerciseModel.programNo,
            exerciseModel.no,
            targetedDate
        ) { nextDate ->
            if (nextDate == null) {
                if (targetedDate != DateUtil.getCurrentDateForRecord()) {
                    Intent(this, RecordExerciseActivity::class.java).apply {
                        putExtra("exTypeModel", exerciseModel)
                        putExtra("targetedDate", DateUtil.getCurrentDateForRecord())
                        startActivity(this)
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        finish()
                    }
                }
            } else {
                Intent(this, RecordExerciseActivity::class.java).apply {
                    putExtra("exTypeModel", exerciseModel)
                    putExtra("targetedDate", nextDate)
                    startActivity(this)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                }
            }
        }
    }

    private fun goPrevious() {
        recordExerciseViewModel.getPreviousDate(
            exerciseModel.programNo,
            exerciseModel.no,
            targetedDate
        ) { previousDate ->
            if (previousDate == null) {
                Toast.makeText(this, "이전 운동 기록이 없습니다!", Toast.LENGTH_SHORT).show()
            } else {
                Intent(this, RecordExerciseActivity::class.java).apply {
                    putExtra("exTypeModel", exerciseModel)
                    putExtra("targetedDate", previousDate)
                    startActivity(this)
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                    finish()
                }
            }
        }
    }

    override fun onBackPressed() {
        if (isPerformedExerciseAtLeastOneSet) {
            setResult(RESULT_OK)
            finish()
        } else {
            super.onBackPressed()
        }
    }

}