package com.progressive.overload.model.model

import java.io.Serializable

data class ExerciseTypeModel(
    val no: Long = 0,
    val name: String?,
    val weight: Float?,
    val repitition: Int?,
    val setNum: Int?,
    val restTime: Int?,
    val rpe: Int?,
    val programNo: Long,
    val mesoCycleSplitIndex: Int?,
    val microCycleSplitIndex: Int?,
    val isPerformed: Boolean = false,
) : Serializable
