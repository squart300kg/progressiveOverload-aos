package com.example.program.ui.home.sub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.repository.RoomRepository
import kotlinx.coroutines.launch

class RegExerciseTypeViewModel(
    val roomRepository: RoomRepository
) : ViewModel() {

    private val TAG = "HomeViewModelLog"

    fun insertExerciseType(excerciseType: ExerciseTypeTable)
        = viewModelScope.launch {
        roomRepository.insertExerciseType(excerciseType)
    }


}