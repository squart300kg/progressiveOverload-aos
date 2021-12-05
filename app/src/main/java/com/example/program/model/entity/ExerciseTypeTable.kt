package com.example.program.model.entity

import androidx.room.*

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
data class ExerciseTypeTable(
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
    val programNo: Long?,
    @ColumnInfo
    val splitTypeIndex: Int?,
)

data class ProgramAndExercise(
    @Embedded val programTable: ProgramTable,

    @Relation(
        parentColumn = "no",
        entityColumn = "programNo"
    )
    val exerciseTypes: List<ExerciseTypeTable>
)