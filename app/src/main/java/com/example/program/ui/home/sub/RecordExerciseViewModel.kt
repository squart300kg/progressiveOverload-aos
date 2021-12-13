package com.example.program.ui.home.sub

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.model.entity.RecordTable
import com.example.program.model.model.ExerciseTypeModel
import com.example.program.model.model.RecordExerciseModel
import com.example.program.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class RecordExerciseViewModel(
    private val roomRepository: RoomRepository,
) : ViewModel() {

    private val _exercises = MutableLiveData<MutableList<ExerciseTypeModel>>()
    val exercises: LiveData<MutableList<ExerciseTypeModel>>
        get() = _exercises

    private val _records = MutableLiveData<MutableList<RecordExerciseModel>>()
    val records: LiveData<MutableList<RecordExerciseModel>>
        get() = _records

    private val TAG = "RecordExerciseVmLog"

    fun initExercise(
        exerciseTable: ExerciseTypeModel,
        recordTable: List<RecordTable>,
    ) {
        val records = mutableListOf<RecordExerciseModel>()
        for (indexX in 0 until exerciseTable.setNum!!) {

            // 이전 운동기록이 있으면 true, 아니면 false
            var isPerformed = false
            for (indexY in recordTable.indices) {
                if (recordTable[indexY].setNum == indexX) {
                    isPerformed = true
                    break
                }
            }

            records.add(
                RecordExerciseModel(
                    indexX,
                    exerciseTable.weight,
                    exerciseTable.repitition,
                    exerciseTable.setNum,
                    8,
                    exerciseTable.restTime,
                    isPerformed
                )
            )
        }
        _records.value = records

        _exercises.value = mutableListOf(exerciseTable)
    }

    fun record(
        model: RecordTable,
        success: () -> Unit,
    ) {
        viewModelScope.launch {
            roomRepository.insertRecord(model)
                .flowOn(Dispatchers.IO)
                .catch { }
                .collect {
                    success()
                }
        }
    }

    fun getTodayExercisePerformed(
        programNo: Long?,
        exerciseNo: Long?,
        success: (list: List<RecordTable>) -> Unit,
    ) {
        viewModelScope.launch {
            roomRepository.getTodayExercisePerformed(programNo, exerciseNo)
                .flowOn(Dispatchers.IO)
                .catch { }
                .collect { response ->
                    Log.i("getTodayExercisePerformed", "result : $response")
                    success(response)
                }
        }
    }
}