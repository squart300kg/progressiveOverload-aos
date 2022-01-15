package com.progressive.overload.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProgramTable(
    @PrimaryKey(autoGenerate = true)
    val no: Long = 0,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val mesoSplitText: String,
    @ColumnInfo
    val mesoSplitCount: Int,
    @ColumnInfo
    val microCycleText: String,
    @ColumnInfo
    val microCycleCount: Int
)
