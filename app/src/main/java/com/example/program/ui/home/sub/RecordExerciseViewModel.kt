package com.example.program.ui.home.sub

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.model.model.RecordExerciseModel

class RecordExerciseViewModel : ViewModel() {

    private val _exercise = MutableLiveData<ExerciseTypeTable>()
    val exercise : LiveData<ExerciseTypeTable>
        get() = _exercise

    private val _exerciseRecord = MutableLiveData<MutableList<RecordExerciseModel>>()
    val exerciseRecord : LiveData<MutableList<RecordExerciseModel>>
        get() = _exerciseRecord

    private val TAG = "RecordExerciseVmLog"

    fun initExInfo(exerciseTable: ExerciseTypeTable) {
        _exercise.value = exerciseTable

        val  recordModel = mutableListOf<RecordExerciseModel>()
        for (index in 0 until exerciseTable.setNum!!) {
            Log.i("loadRecord", exerciseTable.weight.toString())
            _exerciseRecord.value?.add(
                RecordExerciseModel(
                    index,
                    exerciseTable.weight,
                    exerciseTable.repitition,
                    exerciseTable.setNum,
                    8
                )
            )
        }
        _exerciseRecord.value = recordModel
    }
}