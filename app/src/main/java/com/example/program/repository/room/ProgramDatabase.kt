package com.example.program.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.program.api.ProgramDAO
import com.example.program.model.entity.ExerciseTypeTable
import com.example.program.model.entity.ProgramTable

@Database(
    entities = [ProgramTable::class, ExerciseTypeTable::class],
    version = 3, exportSchema = false)
abstract class ProgramDatabase : RoomDatabase() {

    abstract fun programDao(): ProgramDAO

}