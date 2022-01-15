package com.progressive.overload.model.model

import java.io.Serializable

data class ExerciseVolumeModel(
    val name: String,
    val totalVolume: Int
) : Serializable
