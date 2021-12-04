package com.example.program.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExerciseTypeTable(
    @PrimaryKey(autoGenerate = true)
    val no: Int = 0,
    @ColumnInfo
    val name: String?,
    @ColumnInfo
    val weight: Int?,
    @ColumnInfo
    val repitition: Int?,
    @ColumnInfo
    val restTime: Int?,
    @ColumnInfo
    val programNo: Int?,
    @ColumnInfo
    val splitTypeIndex: Int?,

)
