package com.example.uitest.screens

import androidx.lifecycle.ViewModel
import com.example.uitest.data.remote.HistoricalEventApi
import com.example.uitest.domain.HistoricalEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class RandomHistoricalEventViewModel(
    private val historicalEventApi: HistoricalEventApi
) : ViewModel() {

    private val _uiState = MutableStateFlow(ViewState())
    val uiState: StateFlow<ViewState> = _uiState.asStateFlow()

    suspend fun getSingleEvent(): HistoricalEvent {
        val event = historicalEventApi.getHistoricalEvents(
            keyword = " ",
            offset = Random.nextInt(18065)
        )
        return event[0]
    }
}
