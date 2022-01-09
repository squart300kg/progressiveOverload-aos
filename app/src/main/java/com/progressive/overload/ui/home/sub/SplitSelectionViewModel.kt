package com.progressive.overload.ui.home.sub

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.progressive.overload.model.entity.ProgramTable
import com.progressive.overload.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SplitSelectionViewModel @ViewModelInject constructor(
    private val roomRepository: RoomRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val TAG = "SplitSelectionVmLog"

    fun insertProgram(
        program: ProgramTable,
        success: (no: Long) -> Unit,
    ) {
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