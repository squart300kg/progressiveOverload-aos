package com.example.program.di

import com.example.program.repository.RoomRepository
import com.example.program.repository.RoomRepositoryImp
import com.example.program.repository.YoutubeRepository
import com.example.program.repository.YoutubeRepositoryImp
import org.koin.dsl.module

val repositoryModule = module {
    factory <YoutubeRepository> { YoutubeRepositoryImp(get()) }
    factory <RoomRepository> { RoomRepositoryImp(get()) }
}