package com.progressive.overload.model.model

import java.io.Serializable

data class RecordExerciseModel(
    val no : Int,
    val weight : Int,
    val repitition : Int,
    val setNum : Int,
    val rpe : Int,
    val restTime : Int,
    val isPerformed : Boolean = false,
): Serializable
