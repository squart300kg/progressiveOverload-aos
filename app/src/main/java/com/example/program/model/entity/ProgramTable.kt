package com.example.program.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProgramTable(
    @PrimaryKey
    val no: Int,
    @ColumnInfo
    val name: String?,
    @ColumnInfo
    val splitText: String?,
    @ColumnInfo
    val splitCount: Int?

)
