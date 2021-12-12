package com.example.program.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ProgramTable::class,
            parentColumns = arrayOf("no"),
            childColumns = arrayOf("programNo"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RecordTable(
    @PrimaryKey(autoGenerate = true)
    val no: Long = 0,
    @ColumnInfo
    val name: String?,
    @ColumnInfo
    val weight: Int?,
    @ColumnInfo
    val repitition: Int?,
    @ColumnInfo
    val setNum: Int?,
    @ColumnInfo
    val restTime: Int?,
    @ColumnInfo
    val rpe: Int?,
    @ColumnInfo
    val recordTime: Long?,
    @ColumnInfo
    val programNo: Long?,
    @ColumnInfo
    val exerciseTypeNo: Long?,

    )
