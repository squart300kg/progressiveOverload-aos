package com.progressive.overload.ui.record

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.progressive.overload.model.entity.ProgramTable
import com.progressive.overload.model.model.HomeProgramModel
import com.progressive.overload.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class RecordViewModel @ViewModelInject constructor(
    private val roomRepository: RoomRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
)  : ViewModel() {

    private val _programs = MutableLiveData<MutableList<HomeProgramModel>>()
    val programs: LiveData<MutableList<HomeProgramModel>>
        get() = _programs

    fun getAllProgram(
    ) {
        viewModelScope.launch {
            roomRepository.getAllProgram2()
                .flowOn(Dispatchers.IO)
                .catch { }
                .collect {
                    _programs.value = it.toMutableList()
                    Log.i("getAllProgram", it.toString())
                }
        }
    }
}