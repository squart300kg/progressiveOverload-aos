package com.progressive.overload.model.entity

import androidx.room.*
import java.io.Serializable

@Entity(
    indices = [Index(value = ["no", "name"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = ProgramTable::class,
            parentColumns = arrayOf("no"),
            childColumns = arrayOf("programNo"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ExerciseTypeTable(
    @PrimaryKey(autoGenerate = true)
    val no: Long = 0,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    var weight: Float,
    @ColumnInfo
    val repitition: Int,
    @ColumnInfo
    val setNum: Int,
    @ColumnInfo
    val restTime: Int,
    @ColumnInfo
    val rpe: Int,
    @ColumnInfo
    val programNo: Long,
    @ColumnInfo
    val mesoCycleSplitIndex: Int,
    @ColumnInfo
    val microCycleSplitIndex: Int,
) : Serializable
