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
    version = 1, exportSchema = false)
abstract class ProgramDatabase : RoomDatabase() {

    abstract fun programDao(): ProgramDAO

    companion object {
        @Volatile
        private var INSTANCE: ProgramDatabase? = null

        fun getDatabase(context: Context): ProgramDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProgramDatabase::class.java,
                    "program_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}