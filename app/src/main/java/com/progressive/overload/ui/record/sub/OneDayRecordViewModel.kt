package com.progressive.overload.ui.record.sub

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.progressive.overload.model.entity.RecordTable
import com.progressive.overload.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class OneDayRecordViewModel @ViewModelInject constructor(
    private val roomRepository: RoomRepository,
) : ViewModel() {

    private val _records = MutableLiveData<MutableList<RecordTable>>()
    val records: LiveData<MutableList<RecordTable>>
        get() = _records

    private val _names = MutableLiveData<MutableList<String>>()
    val names: LiveData<MutableList<String>>
        get() = _names

    fun getOneDayRecordName(
        programNo: Long?,
        recordTime: String?
    ) {
        viewModelScope.launch {
            roomRepository.getOneDayRecordName(programNo, recordTime)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    e.printStackTrace()
                }
                .collect { records ->
                    Log.i("getOneDayRecordName", records.toString())
                    _names.value = records.toMutableList()
                }
        }
    }

    fun getOneDayRecord(
        name: String?,
        programNo: Long?,
        recordTime: String?,
        success : (List<RecordTable>) -> Unit

    ) {
        viewModelScope.launch {
            roomRepository.getOneDayRecord(name, programNo, recordTime)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    e.printStackTrace()
                }
                .collect { records ->
                    Log.i("getOneDayRecord", records.toString())
                    _records.value = records.toMutableList()
                    success(records)
                }
        }
    }

    fun getPreviousDate(
        targetedProgramNo: Long?,
        targetedDate: String?,
        success: (String?) -> Unit = {}
    ) {
        viewModelScope.launch {
            roomRepository.getPreviousDate(targetedProgramNo, targetedDate)
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
        targetedDate: String?,
        success: (String?) -> Unit = {}
    ) {
        viewModelScope.launch {
            roomRepository.getNextDate(targetedProgramNo, targetedDate)
                .flowOn(Dispatchers.IO)
                .catch { }
                .collect { date ->
                    Log.i("getTargetDate", "getNext $date")
                    success(date)
                }
        }
    }
}