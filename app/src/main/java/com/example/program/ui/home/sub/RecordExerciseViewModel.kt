package com.example.program.ui.home.sub

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.program.model.entity.RecordTable
import com.example.program.model.model.ExerciseTypeModel
import com.example.program.model.model.RecordExerciseModel
import com.example.program.repository.RoomRepository
import com.example.program.util.DateUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class RecordExerciseViewModel @ViewModelInject constructor(
    private val roomRepository: RoomRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _exercises = MutableLiveData<MutableList<ExerciseTypeModel>>()
    val exercises: LiveData<MutableList<ExerciseTypeModel>>
        get() = _exercises

    private val _records = MutableLiveData<MutableList<RecordExerciseModel>>()
    val records: LiveData<MutableList<RecordExerciseModel>>
        get() = _records

    private val _dates = MutableLiveData<MutableList<String>>()
    val dates: LiveData<MutableList<String>>
        get() = _dates

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

    fun getTargetedExercisePerformed(
        targetedProgramNo: Long?,
        targetedExerciseNo: Long?,
        targetedDate: String?,
        success: (list: List<RecordTable>) -> Unit,
    ) {
        viewModelScope.launch {
            roomRepository.getTargetedExercisePerformed(
                targetedProgramNo,
                targetedExerciseNo,
                targetedDate
            )
                .flowOn(Dispatchers.IO)
                .catch { }
                .collect { response ->
                    success(response)
                }
        }
    }

    fun getTargetedAllDate(programNo: Long?, exerciseNo: Long?) {
        viewModelScope.launch {
            roomRepository.getTargetedAllDate(programNo, exerciseNo)
                .flowOn(Dispatchers.IO)
                .catch { }
                .collect { responseDates ->

                    var tempDates = responseDates.toMutableList()
                    if (responseDates.isNotEmpty() && responseDates[0] != DateUtil.getCurrentDateForRecord()) {
                        // 값이 있는데, 첫 인덱스가 오늘 날짜가 아닌 경우

                        tempDates.add(0, DateUtil.getCurrentDateForRecord())
                        _dates.value = tempDates

                    } else if (responseDates.isNullOrEmpty()) {
                        // 값이 비어있는 경우

                        tempDates.add(0, DateUtil.getCurrentDateForRecord())
                        _dates.value = tempDates

                    } else {
                        // 값이 있고, 첫 인덱스가 오늘 날짜인 경우

                        _dates.value = responseDates.toMutableList()
                    }
                }
        }
    }

    fun getPreviousDate(
        targetedProgramNo: Long?,
        targetedExerciseNo: Long?,
        targetedDate: String?,
        success: (String?) -> Unit = {}
    ) {
        viewModelScope.launch {
            roomRepository.getPreviousDate(targetedProgramNo, targetedExerciseNo, targetedDate)
                .flowOn(Dispatchers.IO)
                .catch { }
                .collect { date ->
                    Log.i("getTargetDate", "getPrevious $date")
                    success(date)
                }
        }
    }

    fun getNextDate(
        targetedProgramNo: Long?,
        targetedExerciseNo: Long?,
        targetedDate: String?,
        success: (String?) -> Unit = {}
    ) {
        viewModelScope.launch {
            roomRepository.getNextDate(targetedProgramNo, targetedExerciseNo, targetedDate)
                .flowOn(Dispatchers.IO)
                .catch { }
                .collect { date ->
                    Log.i("getTargetDate", "getNext $date")
                    success(date)
                }
        }
    }

}