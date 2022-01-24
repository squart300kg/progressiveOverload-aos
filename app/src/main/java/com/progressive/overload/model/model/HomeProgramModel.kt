package com.progressive.overload.model.model

data class HomeProgramModel(
    val no: Long = 0,
    val name: String,
    val mesoSplitText: String,
    val mesoSplitCount: Int,
    val microCycleText: String,
    val microCycleCount: Int,
    val isDummy: Boolean = false,
    val isDummyDataInit: Boolean = false,
    val performedPercentage: Int
)
