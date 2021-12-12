package com.example.program.ui.home.sub

import android.os.Bundle
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivityRecordExerciseBinding
import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.model.entity.RecordTable
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecordExerciseActivity :
    BaseActivity<ActivityRecordExerciseBinding>(R.layout.activity_record_exercise) {

    private val recordExerciseViewModel: RecordExerciseViewModel by viewModel()

    private lateinit var recordExerciseAdapter: RecordExerciseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val exerciseTable = intent.getSerializableExtra("exerciseTable") as ExerciseTypeTable

        binding {
            recordExVm = recordExerciseViewModel

            rvRecordEx.apply {
                setHasFixedSize(true)
                recordExerciseAdapter = RecordExerciseAdapter(this@RecordExerciseActivity) { model ->
                    recordExerciseViewModel.record(
                        RecordTable(
                            name = exerciseTable.name,
                            weight = model.weight,
                            repitition = model.repitition,
                            setNum = model.no,
                            restTime = model.restTime,
                            rpe = model.rpe,
                            recordTime = System.currentTimeMillis(),
                            programNo = exerciseTable.programNo,
                            exerciseTypeNo = exerciseTable.no
                        )
                    ){
                        recordExerciseAdapter.successExercise()
                    }
                }
                adapter = recordExerciseAdapter
            }
        }

        recordExerciseViewModel.initExInfo(exerciseTable)

    }

}