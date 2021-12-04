package com.example.program.di

import com.example.program.repository.room.ProgramDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val roomModule = module {
    single {
        ProgramDatabase.getDatabase(androidApplication())
    }
}