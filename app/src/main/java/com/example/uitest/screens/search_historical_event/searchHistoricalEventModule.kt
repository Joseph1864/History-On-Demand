package com.example.uitest.screens.search_historical_event

import com.example.uitest.domain.HistoricalEventRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchHistoricalEventModule = module {
    viewModel {
        SearchHistoricalEventViewModel(get<HistoricalEventRepository>())
    }
}