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

class RecordDetailViewModel(
    private val roomRepository: RoomRepository,
) : ViewModel() {

    private val _records = MutableLiveData<MutableList<RecordModel>>()
    val records: LiveData<MutableList<RecordModel>>
        get() = _records

    private val TAG = "RecordDetailVmLog"

    fun getAllRecordsDateByProgramNo(
        programNo: Long,
        success: (dates: List<String>) -> Unit,
    ) {
        viewModelScope.launch {
            roomRepository.getAllRecordsDateByProgramNo(programNo)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    e.printStackTrace()
                }
                .collect { recordDates ->

                    success(recordDates)
                }
        }
    }

    fun getTargetDateTotalVolume(
        programNo: Long,
        date: String,
        success: (volume : Int) -> Unit) {
        viewModelScope.launch {
            roomRepository.getTargetDateTotalVolume(programNo, date)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    e.printStackTrace()
                }
                .collect { totalVolume ->
                    Log.i("totalVolume :", totalVolume.toString())
                    success(totalVolume)
                }
        }
    }

    fun initRecord(volume: Int, date: String) {
        val records = mutableListOf<RecordModel>()
        records.add(
            RecordModel(
                volume,
                date
            )
        )

        _records.value = records
    }

}