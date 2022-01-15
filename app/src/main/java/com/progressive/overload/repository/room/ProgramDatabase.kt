package com.progressive.overload.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.progressive.overload.api.ProgramDAO
import com.progressive.overload.model.entity.ExerciseTypeTable
import com.progressive.overload.model.entity.ProgramTable
import com.progressive.overload.model.entity.RecordTable

@Database(
    entities = [ProgramTable::class, ExerciseTypeTable::class, RecordTable::class],
    version = 19, exportSchema = false)
abstract class ProgramDatabase : RoomDatabase() {

    abstract fun programDao(): ProgramDAO

}