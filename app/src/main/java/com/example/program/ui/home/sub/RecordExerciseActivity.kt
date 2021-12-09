package com.example.program.ui.home.sub

import android.os.Bundle
import android.util.Log
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivityRecordExerciseBinding
import com.example.program.model.entity.ExerciseTypeTable
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecordExerciseActivity :
    BaseActivity<ActivityRecordExerciseBinding>(R.layout.activity_record_exercise) {

    private val recordExerciseViewModel: RecordExerciseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val exerciseTable = intent.getSerializableExtra("exerciseTable") as ExerciseTypeTable

        Log.i("loadRecord", exerciseTable.toString())

        binding {
            recordExVm = recordExerciseViewModel

            rvRecordEx.apply {
                setHasFixedSize(true)
                adapter = RecordExerciseAdapter {
                    Log.i("RecordExerciseActivity", "hello")
                }
            }
        }

        recordExerciseViewModel.initExInfo(exerciseTable)

    }

}