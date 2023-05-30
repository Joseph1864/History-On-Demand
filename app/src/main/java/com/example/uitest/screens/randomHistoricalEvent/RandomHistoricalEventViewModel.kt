package com.example.uitest.screens.randomHistoricalEvent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uitest.data.remote.HistoricalEventApi
import com.example.uitest.domain.HistoricalEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class RandomHistoricalEventViewModel(
    private val historicalEventApi: HistoricalEventApi
) : ViewModel() {

    private val _uiState: MutableStateFlow<RandomHistoricalEventViewState> =
        MutableStateFlow(RandomHistoricalEventViewState.Loading)
    val uiState: StateFlow<RandomHistoricalEventViewState> = _uiState.asStateFlow()

    init {
        generateRandomEvent()
    }

    fun generateEventClicked() {
        generateRandomEvent()
    }

    private fun generateRandomEvent() = viewModelScope.launch(
        CoroutineExceptionHandler { _, exception ->
            _uiState.update {
                RandomHistoricalEventViewState.Error
            }
        }
    ) {
        _uiState.update {
            RandomHistoricalEventViewState.Loading
        }
        val event = historicalEventApi.getHistoricalEvents(
            keyword = " ",
            offset = Random.nextInt(18065)
        )

        _uiState.update {
            RandomHistoricalEventViewState.Content(event[0])
        }
    }
}

sealed class RandomHistoricalEventViewState {
    object Error : RandomHistoricalEventViewState()
    object Loading : RandomHistoricalEventViewState()
    data class Content(val event: HistoricalEvent) : RandomHistoricalEventViewState()
}
