package com.example.program.di

import com.example.program.api.ProgramDAO
import com.example.program.api.YoutubeApi
import com.example.program.repository.RoomRepository
import com.example.program.repository.RoomRepositoryImp
import com.example.program.repository.YoutubeRepository
import com.example.program.repository.YoutubeRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


//object RepositoryModule = module {
//    factory <YoutubeRepository> { YoutubeRepositoryImp(get()) }
//    factory <RoomRepository> { RoomRepositoryImp(get()) }
//}

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideYoutubeRepository(youtubeApi: YoutubeApi): YoutubeRepository {
        return YoutubeRepositoryImp(youtubeApi)
    }

    @Singleton
    @Provides
    fun provideRoomRepository(programDao: ProgramDAO): RoomRepository {
        return RoomRepositoryImp(programDao)
    }
}