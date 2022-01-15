package com.progressive.overload.di

import com.progressive.overload.api.ProgramDAO
import com.progressive.overload.api.YoutubeApi
import com.progressive.overload.repository.RoomRepository
import com.progressive.overload.repository.RoomRepositoryImp
import com.progressive.overload.repository.YoutubeRepository
import com.progressive.overload.repository.YoutubeRepositoryImp
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