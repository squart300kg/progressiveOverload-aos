package com.example.program.ui.home.sub

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.model.entity.RecordTable
import com.example.program.model.model.RecordExerciseModel
import com.example.program.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class RecordExerciseViewModel(
    private val roomRepository: RoomRepository
) : ViewModel() {

    private val _exercise = MutableLiveData<MutableList<ExerciseTypeTable>>()
    val exercise : LiveData<MutableList<ExerciseTypeTable>>
        get() = _exercise

    private val TAG = "RecordExerciseVmLog"

    fun initExInfo(exerciseTable: ExerciseTypeTable) {

        _exercise.value = mutableListOf(exerciseTable)
    }

    fun record(
        model: RecordTable,
        success : () -> Unit) {
        viewModelScope.launch {
            roomRepository.insertRecord(model)
                .flowOn(Dispatchers.IO)
                .catch {  }
                .collect {
                    success()
                }
        }
    }
}