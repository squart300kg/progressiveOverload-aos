package com.example.program.di

import com.example.program.ui.home.HomeViewModel
import com.example.program.ui.home.sub.RegExerciseTypeViewModel
import com.example.program.ui.message.MessageViewModel
import com.example.program.ui.mypage.MyPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel() }
    viewModel { MessageViewModel() }
    viewModel { MyPageViewModel() }
    viewModel { RegExerciseTypeViewModel(get()) }
}