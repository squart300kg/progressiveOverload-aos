package com.example.program.ui.home.sub

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.model.model.ExerciseTypeModel
import com.example.program.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class RegExerciseTypeViewModel(
    private val roomRepository: RoomRepository
) : ViewModel() {

    private val TAG = "HomeViewModelLog"

    private val _exercises = MutableLiveData<MutableList<ExerciseTypeModel>>()
    val exercises: LiveData<MutableList<ExerciseTypeModel>>
        get() = _exercises

    private val _exercise = MutableLiveData<ExerciseTypeModel>()
    val exercise : LiveData<ExerciseTypeModel>
        get() = _exercise

    fun insertExerciseType(
        excerciseType: ExerciseTypeTable) {
        viewModelScope.launch {
            roomRepository.insertExerciseType(excerciseType)
                .flowOn(Dispatchers.IO)
                .catch {  }
                .collect {
                    Log.i("insertExerciseType", it.toString())
                }
        }
    }

    fun deleteProgram(
        programNo: Long?,
        success: () -> Unit
    ) {
        viewModelScope.launch {
            roomRepository.deleteProgram(programNo)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    e.printStackTrace()
                }
                .collect {
                    success()
                }
        }
    }

    fun deleteExercise(
        exerciseTypeModel: ExerciseTypeModel?,
        success: () -> Unit
    ) {
        viewModelScope.launch {
            roomRepository.deleteExercise(exerciseTypeModel)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    e.printStackTrace()
                }
                .collect {
                    success()
                }
        }
    }

    fun getExercises(
        programNo: Long?,
        splitIndex: Int?,
        success: (exercises : List<ExerciseTypeModel>) -> Unit = { }) {
        viewModelScope.launch {
            roomRepository.getExercises(programNo, splitIndex)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    e.printStackTrace()
                }
                .collect { exercises ->

                    _exercises.value = exercises.toMutableList()

                    success(exercises)
                }
        }
    }

    fun getExercisePerformedStatuses(
        programNo: Long?,
        exerciseNo : Long?,
        success: (statuses : Int) -> Unit
    ) {
        viewModelScope.launch {
            roomRepository.getExercisePerformedStatuses(programNo, exerciseNo)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    e.printStackTrace()
                }
                .collect { statuses ->
                    success(statuses)
                }
        }
    }

    fun updateProgramName(
        name : String,
        programNo: Long?,
        success: () -> Unit) {
        viewModelScope.launch {
            roomRepository.updateProgramName(name, programNo)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    e.printStackTrace()
                }
                .collect {
                    Log.i("updateProgramName", it.toString())
                    success()
                }
        }
    }

    fun updateExercise(
        exerciseTypeTable: ExerciseTypeTable?,
        success: () -> Unit) {
        viewModelScope.launch {
            roomRepository.updateExercise(exerciseTypeTable)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    e.printStackTrace()
                }
                .collect {
                    success()
                }
        }
    }

    fun setExerciseInfo(exerciseTypeTable: ExerciseTypeModel) {
        _exercise.value = exerciseTypeTable
    }
}