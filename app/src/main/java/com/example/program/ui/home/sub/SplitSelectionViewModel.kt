package com.example.program.ui.home.sub

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.program.model.entity.ProgramTable
import com.example.program.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SplitSelectionViewModel(
    private val roomRepository: RoomRepository
) : ViewModel() {

    private val TAG = "SplitSelectionVmLog"

    fun insertProgram(
        program : ProgramTable,
        success : (no : Long) -> Unit) {
        viewModelScope.launch {
            roomRepository.insertProgram(program)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    e.printStackTrace()
                }
                .collect {
                    success(it)
                    Log.i("insertProgram", it.toString())
                }
        }
    }

}