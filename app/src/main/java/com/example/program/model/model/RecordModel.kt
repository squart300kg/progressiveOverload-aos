package com.example.program.model.model

import java.io.Serializable

data class RecordModel(
    val volume : Int,
    val recordTime : String
) : Serializable
