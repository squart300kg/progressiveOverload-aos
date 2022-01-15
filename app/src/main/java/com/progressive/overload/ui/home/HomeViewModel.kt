package com.progressive.overload.ui.home

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.progressive.overload.base.BaseViewModel
import com.progressive.overload.model.entity.ProgramTable
import com.progressive.overload.repository.RoomRepository
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

    fun duplicateProgram(programNo: Long,
                         name: String,
                         success: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            roomRepository.duplicateProgram(programNo, name)
                .flowOn(Dispatchers.IO)
                .onCompletion { cause ->
                    Log.i("duplicateProgram", "duplicateProgram")
                    if (cause == null)
                        success()
                }
                .catch { }
                .collect {
                    Log.i("duplicateProgram", it.toString())
                }
            _isLoading.value = false
        }
    }
}