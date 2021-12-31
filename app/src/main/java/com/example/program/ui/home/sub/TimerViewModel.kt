package com.example.program.ui.home.sub

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.program.repository.RoomRepository

class TimerViewModel @ViewModelInject constructor(
    private val roomRepository: RoomRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _timer = MutableLiveData<Int>()
    val timer: LiveData<Int>
        get() = _timer

    private val _maxTime = MutableLiveData<Int>()
    val maxTime: LiveData<Int>
        get() = _maxTime

    fun initMaxTime(number: Int) {
        _maxTime.value = number
    }

    fun initTimerNumber(number: Int) {
        _timer.value = number
    }

}