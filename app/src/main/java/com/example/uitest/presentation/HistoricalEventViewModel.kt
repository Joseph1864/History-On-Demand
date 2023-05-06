package com.example.uitest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.uitest.data.remote.HistoricalEventRepository
import com.example.uitest.domain.HistoricalEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HistoricalEventViewModel(
    private val repository: HistoricalEventRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ViewState())
    val uiState: StateFlow<ViewState> = _uiState.asStateFlow()

    private val _historicalEventPagingFlow: MutableStateFlow<PagingData<HistoricalEvent>> =
        MutableStateFlow(PagingData.empty())
    var historicalEventPagingFlow = _historicalEventPagingFlow.asStateFlow()

    init {
        viewModelScope.launch {
            repository.events("King Charles")
                .cachedIn(viewModelScope)
                .collect(_historicalEventPagingFlow)
        }
    }

    fun onSearchTextChanged(text: String) {
        _uiState.update {
            it.copy(
                searchText = text
            )
        }
    }

    fun onSearchClicked() = viewModelScope.launch {
        repository.events(_uiState.value.searchText)
            .cachedIn(viewModelScope)
            .collect(_historicalEventPagingFlow)
    }
}

data class ViewState(
    val searchText: String = "",
    val events: PagingData<HistoricalEvent> = PagingData.empty()
)

