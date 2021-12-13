package com.example.program.ui.home.sub

import android.os.Bundle
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivityRecordExerciseBinding
import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.model.entity.RecordTable
import com.example.program.model.model.ExerciseTypeModel
import com.example.program.util.DateUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecordExerciseActivity :
    BaseActivity<ActivityRecordExerciseBinding>(R.layout.activity_record_exercise) {

    private val recordExerciseViewModel: RecordExerciseViewModel by viewModel()

    private lateinit var recordExerciseAdapter: RecordExerciseAdapter

    private lateinit var exerciseModel: ExerciseTypeModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exerciseModel = intent.getSerializableExtra("exTypeModel") as ExerciseTypeModel

        binding {
            recordExVm = recordExerciseViewModel

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
                            recordExerciseAdapter.successExercise()
                        }
                    }
                adapter = recordExerciseAdapter

            }
        }

        recordExerciseViewModel.getTodayExercisePerformed(
            exerciseModel.programNo,
            exerciseModel.no
        ) { recordTable ->
            recordExerciseViewModel.initExercise(exerciseModel, recordTable)
        }
    }

}