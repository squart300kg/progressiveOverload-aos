package com.example.program.ui.home

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.program.base.BaseViewModel
import com.example.program.model.entity.ProgramTable
import com.example.program.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val roomRepository: RoomRepository,
)  : BaseViewModel() {

    private val _programs = MutableLiveData<MutableList<ProgramTable>>()
    val programs: LiveData<MutableList<ProgramTable>>
        get() = _programs

    private val TAG = "HomeViewModelLog"

    fun getAllProgram() {
        viewModelScope.launch {
            _isLoading.value = true
            roomRepository.getAllProgram()
                .flowOn(Dispatchers.IO)
                .catch { }
                .collect {
                    _programs.value = it.toMutableList()
                    Log.i("getAllProgram", it.toString())
                }
            _isLoading.value = false
        }
    }

    fun deleteProgram(
        programNo: Long,
        success: () -> Unit,
    ) {
        viewModelScope.launch {
            roomRepository.deleteProgram(programNo)
                .flowOn(Dispatchers.IO)
                .catch { }
                .onCompletion {
                    Log.i("deleteProgram", "deleteSuccess")
                    success()
                }
                .collect {
                    Log.i("deleteProgram", it.toString())
                }
        }
    }
}