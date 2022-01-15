package com.progressive.overload.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ExerciseTypeTable::class,
            parentColumns = arrayOf("no", "name"),
            childColumns = arrayOf("exerciseTypeNo", "name"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
    ]
)
data class RecordTable(
    @PrimaryKey(autoGenerate = true)
    val no: Long = 0,
    @ColumnInfo
    val name: String?,
    @ColumnInfo
    val weight: Float?,
    @ColumnInfo
    val repitition: Int?,
    @ColumnInfo
    val setNum: Int?,
    @ColumnInfo
    val restTime: Int?,
    @ColumnInfo
    val rpe: Int?,
    @ColumnInfo
    val recordTime: String?,
    @ColumnInfo
    val programNo: Long?,
    @ColumnInfo
    val exerciseTypeNo: Long?,

    )
