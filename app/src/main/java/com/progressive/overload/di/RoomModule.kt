package com.progressive.overload.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.progressive.overload.api.ProgramDAO
import com.progressive.overload.repository.room.ProgramDatabase
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
            "program_database.db"
        )
//            .addMigrations(MIGRATION_6_7)
//            .createFromAsset("database/hoya_dummy_room_data_v_19.db")
            .createFromAsset("database/hyuk_dummy_room_data_v_19.db")
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

    val MIGRATION_6_7 = object : Migration(6, 7) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE recordTable ADD ForeignKey name references ExerciseTypeTable(name) on delete cascade on update cascade")
        }
    }

}
