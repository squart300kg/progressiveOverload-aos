package com.example.program.di

import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.program.repository.room.ProgramDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val roomModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            ProgramDatabase::class.java,
            "program_database"
        )
            .fallbackToDestructiveMigration()
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Log.i("roomDBLog", "roomDB생성!!")
                }
            })
            .build()
    }
    single {
        get<ProgramDatabase>().programDao()
    }

}