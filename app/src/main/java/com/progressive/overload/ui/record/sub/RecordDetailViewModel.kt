package com.progressive.overload.ui.record.sub

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.progressive.overload.model.model.ExerciseVolumeModel
import com.progressive.overload.model.model.RecordModel
import com.progressive.overload.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class RecordDetailViewModel @ViewModelInject constructor(
    private val roomRepository: RoomRepository,
) : ViewModel() {

    private val _records = MutableLiveData<MutableList<RecordModel>>()
    val records: LiveData<MutableList<RecordModel>>
        get() = _records

    private val _exerciseVolumes = MutableLiveData<MutableList<ExerciseVolumeModel>>()
    val exerciseVolumes: LiveData<MutableList<ExerciseVolumeModel>>
        get() = _exerciseVolumes

    private val TAG = "RecordDetailVmLog"

    fun getAllRecordsDateByProgramNo(
        programNo: Long,
    ) {
        viewModelScope.launch {
            roomRepository.getAllRecordsDateByProgramNo(programNo)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    e.printStackTrace()
                }
                .collect { recordDates ->
                    Log.i("recordDates", "collect : "+recordDates.toString())
                    _records.value = recordDates.toMutableList()
                }
        }
    }

    fun getExerciseVolumes(
        programNo: Long,
        recordTime: String,
        success: (List<ExerciseVolumeModel>) -> Unit,
    ) {
        viewModelScope.launch {
            roomRepository.getExerciseVolumes(programNo, recordTime)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    e.printStackTrace()
                }
                .collect { exerciseVolumes ->
                    success(exerciseVolumes)
                }
        }

    }
}