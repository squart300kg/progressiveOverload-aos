package com.example.program.ui.home.sub

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.program.base.BaseViewModel
import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.model.model.ExerciseTypeModel
import com.example.program.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class RegExerciseTypeViewModel @ViewModelInject constructor(
    private val roomRepository: RoomRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val TAG = "HomeViewModelLog"

    private val _exercises = MutableLiveData<MutableList<ExerciseTypeModel>>()
    val exercises: LiveData<MutableList<ExerciseTypeModel>>
        get() = _exercises

    private val _exercise = MutableLiveData<ExerciseTypeModel>()
    val exercise: LiveData<ExerciseTypeModel>
        get() = _exercise

    fun insertExerciseType(
        excerciseType: ExerciseTypeTable,
        success: () -> Unit
    ) {
        viewModelScope.launch {
            roomRepository.insertExerciseType(excerciseType)
                .flowOn(Dispatchers.IO)
                .catch { }
                .collect {
                    Log.i("insertExerciseType", it.toString())
                    success()
                }
        }
    }

    fun deleteProgram(
        programNo: Long?,
        success: () -> Unit,
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
        success: () -> Unit,
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
        programNo: Long,
        mesoCycleSplitIndex: Int,
        microCycleSplitIndex: Int,
        success: (exercises: List<ExerciseTypeModel>) -> Unit = { },
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            roomRepository.getExercises(programNo, mesoCycleSplitIndex, microCycleSplitIndex)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    e.printStackTrace()
                }
                .collect { exercises ->

                    Log.i("getExercises", "$exercises")
                    _exercises.value = exercises.toMutableList()

                    success(exercises)
                }
            _isLoading.value = false

        }
    }

    fun getPerformedSets(
        programNo: Long?,
        exerciseNo: Long?,
        success: (statuses: Int) -> Unit,
    ) {
        viewModelScope.launch {
            roomRepository.getPerformedSets(programNo, exerciseNo)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    e.printStackTrace()
                }
                .collect { sets ->
                    success(sets)
                }
        }
    }

    fun updateProgramName(
        programNo: Long?,
        name: String,
        success: () -> Unit,
    ) {
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
        exerciseTypeTable: ExerciseTypeTable,
        success: () -> Unit,
    ) {
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

    fun setExerciseInfo(model: ExerciseTypeModel) {
        _exercise.value = model
    }

    fun initExerciePerformTrue(indexX: Int) {
        val exercises = mutableListOf<ExerciseTypeModel>()
        _exercises.value?.forEachIndexed { indexY, it ->
            Log.i("statuses",
                "indexX : $indexX, indexY ; $indexY, indexX == indexY ; ${indexX == indexY}")
            exercises.add(
                ExerciseTypeModel(
                    no = it.no,
                    name = it.name,
                    weight = it.weight,
                    repitition = it.repitition,
                    setNum = it.setNum,
                    restTime = it.restTime,
                    rpe = it.rpe,
                    programNo = it.programNo,
                    mesoCycleSplitIndex = it.mesoCycleSplitIndex,
                    microCycleSplitIndex = it.microCycleSplitIndex,
                    isPerformed = indexX == indexY
                )
            )
        }
        _exercises.value = exercises
    }

    fun initPerformedExerciesSetTrue(
        indexes: List<Int>,
        success: () -> Unit,
    ) {
        Log.i("statuses", "viewModel들어옴")

        val exercises = mutableListOf<ExerciseTypeModel>()
        _exercises.value?.forEachIndexed { indexX, it ->
            exercises.add(
                ExerciseTypeModel(
                    no = it.no,
                    name = it.name,
                    weight = it.weight,
                    repitition = it.repitition,
                    setNum = it.setNum,
                    restTime = it.restTime,
                    rpe = it.rpe,
                    programNo = it.programNo,
                    mesoCycleSplitIndex = it.mesoCycleSplitIndex,
                    microCycleSplitIndex = it.microCycleSplitIndex,
                    isPerformed = indexes.contains(indexX)
                )
            )
            Log.i("statuses", "viewModel - isPerformed[$indexX] : ${exercises[indexX].isPerformed}")

        }
        _exercises.value = exercises
        success()
    }
}