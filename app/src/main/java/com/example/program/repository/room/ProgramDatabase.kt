package com.example.program.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.program.api.ProgramDAO
import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.model.entity.ProgramTable
import com.example.program.model.entity.RecordTable

@Database(
    entities = [ProgramTable::class, ExerciseTypeTable::class, RecordTable::class],
    version = 6, exportSchema = false)
abstract class ProgramDatabase : RoomDatabase() {

    abstract fun programDao(): ProgramDAO

}