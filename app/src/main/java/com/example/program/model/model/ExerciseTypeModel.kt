package com.example.program.model.model

import java.io.Serializable

data class ExerciseTypeModel(
    val no: Long = 0,
    val name: String?,
    val weight: Int?,
    val repitition: Int?,
    val setNum: Int?,
    val restTime: Int?,
    val programNo: Long?,
    val splitTypeIndex: Int?,
    var isPerformed: Boolean = false,
) : Serializable
