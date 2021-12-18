package com.example.program.ui.record.sub

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.program.model.entity.RecordTable
import com.example.program.model.model.RecordModel
import com.example.program.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class OneDayRecordViewModel(
    private val roomRepository: RoomRepository,
) : ViewModel() {

    private val _records = MutableLiveData<MutableList<RecordTable>>()
    val records: LiveData<MutableList<RecordTable>>
        get() = _records

    fun getOneDayRecord(
        programNo: Long?,
        recordTime: String?
    ) {
        viewModelScope.launch {
            roomRepository.getOneDayRecord(programNo, recordTime)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    e.printStackTrace()
                }
                .collect { records ->
                    Log.i("getOneDayRecord", records.toString())
                    _records.value = records.toMutableList()
                }
        }
    }
}