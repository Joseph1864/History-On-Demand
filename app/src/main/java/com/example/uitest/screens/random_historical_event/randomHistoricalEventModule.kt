package com.example.uitest.screens.random_historical_event

import com.example.uitest.data.remote.HistoricalEventApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val randomHistoricalEventModule = module {
    viewModel {
        RandomHistoricalEventViewModel(get<HistoricalEventApi>())
    }
}