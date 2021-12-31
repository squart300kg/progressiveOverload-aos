package com.example.program.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.program.api.ProgramDAO
import com.example.program.repository.room.ProgramDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): ProgramDatabase {
        return Room.databaseBuilder(
            appContext,
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

    @Provides
    fun provideProgramDao(database: ProgramDatabase): ProgramDAO {
        return database.programDao()
    }

}
