package com.progressive.overload.ui.home.sub

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.progressive.overload.model.entity.RecordTable
import com.progressive.overload.model.model.ExerciseTypeModel
import com.progressive.overload.model.model.RecordExerciseModel
import com.progressive.overload.repository.RoomRepository
import com.progressive.overload.util.DateUtil
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
        exerciseTypeModel: ExerciseTypeModel,
        recordTable: List<RecordTable>,
    ) {
        val records = mutableListOf<RecordExerciseModel>()
        for (indexX in 0 until exerciseTypeModel.setNum!!) {

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
                    exerciseTypeModel.weight!!,
                    exerciseTypeModel.repitition!!,
                    exerciseTypeModel.setNum,
                    exerciseTypeModel.rpe!!,
                    exerciseTypeModel.restTime!!,
                    isPerformed
                )
            )
        }
        _records.value = records

        _exercises.value = mutableListOf(exerciseTypeModel)

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
        targetedProgramNo: Long,
        targetedExerciseNo: Long,
        success: (list: List<RecordTable>) -> Unit,
    ) {
        viewModelScope.launch {
            roomRepository.getTargetedExercisePerformed(
                targetedProgramNo,
                targetedExerciseNo,
            )
                .flowOn(Dispatchers.IO)
                .catch { }
                .collect { response ->
                    success(response)
                }
        }
    }

    // TODO 이전기록, 이후기록 불러오기 기능 추후 오픈시 주석 해제
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

    // TODO 이전기록, 이후기록 불러오기 기능 추후 오픈시 주석 해제
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

    // TODO 이전기록, 이후기록 불러오기 기능 추후 오픈시 주석 해제
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

    // TODO 이전기록, 이후기록 불러오기 기능 추후 오픈시 주석 해제
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