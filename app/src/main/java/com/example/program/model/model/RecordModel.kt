package com.example.program.model.model

import java.io.Serializable

data class RecordModel(
    val totalVolume : Int,
    val recordTime : String,
) : Serializable
