package com.example.program.di

import com.example.program.ui.home.HomeViewModel
import com.example.program.ui.home.sub.RecordExerciseViewModel
import com.example.program.ui.home.sub.RegExerciseTypeViewModel
import com.example.program.ui.home.sub.SplitSelectionViewModel
import com.example.program.ui.record.RecordViewModel
import com.example.program.ui.mypage.MyPageViewModel
import com.example.program.ui.record.sub.RecordDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel() }
    viewModel { RecordViewModel() }
    viewModel { MyPageViewModel() }
    viewModel { RegExerciseTypeViewModel(get()) }
    viewModel { SplitSelectionViewModel(get()) }
    viewModel { RecordExerciseViewModel(get()) }
    viewModel { RecordDetailViewModel(get()) }
}