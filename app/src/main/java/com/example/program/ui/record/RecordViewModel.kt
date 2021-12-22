package com.example.program.ui.record

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.program.model.entity.ProgramTable
import com.example.program.model.entity.RecordTable
import com.example.program.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

class RecordViewModel : ViewModel() {

    private val roomRepository: RoomRepository by KoinJavaComponent.inject(RoomRepository::class.java)

    private val _programs = MutableLiveData<MutableList<ProgramTable>>()
    val programs: LiveData<MutableList<ProgramTable>>
        get() = _programs

    fun getAllProgram(
    ) {
        viewModelScope.launch {
            roomRepository.getAllProgram()
                .flowOn(Dispatchers.IO)
                .catch { }
                .collect {
                    _programs.value = it.toMutableList()
                    Log.i("getAllProgram", it.toString())
                }
        }
    }
}