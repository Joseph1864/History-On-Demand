package com.example.uitest.screens.searchHistoricalEvent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.uitest.data.remote.HistoricalEventRepository
import com.example.uitest.domain.HistoricalEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchHistoricalEventViewModel(
    private val repository: HistoricalEventRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchHistoricalEventViewState())
    val uiState: StateFlow<SearchHistoricalEventViewState> = _uiState.asStateFlow()


    private val _historicalEventPagingFlow: MutableStateFlow<PagingData<HistoricalEvent>> =
        MutableStateFlow(PagingData.empty())
    var historicalEventPagingFlow = _historicalEventPagingFlow.asStateFlow()

    init {
        viewModelScope.launch {
            repository.clearCache()
            _uiState.map { it.searchText }
                .debounce(400)
                .collectLatest {
                    repository.events(it)
                        .cachedIn(viewModelScope)
                        .collect(_historicalEventPagingFlow)
                }

        }
    }

    fun onSearchTextChanged(text: String) = viewModelScope.launch {
        _uiState.update {
            it.copy(
                searchText = text
            )
        }
    }
}

data class SearchHistoricalEventViewState(
    val searchText: String = "",
    val events: PagingData<HistoricalEvent> = PagingData.empty(),
)

