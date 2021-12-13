package com.example.program.model.model

data class RecordExerciseModel(
    val no : Int?,
    val weight : Int?,
    val repitition : Int?,
    val setNum : Int?,
    val rpe : Int?,
    val restTime : Int?,
    val isPerformed : Boolean = false,
)
