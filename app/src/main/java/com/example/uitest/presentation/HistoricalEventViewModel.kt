package com.example.uitest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.uitest.data.local.HistoricalEventEntity
import com.example.uitest.data.mappers.toHistoricalEvent
import com.example.uitest.data.remote.HistoricalEventRemoteMediator
import com.example.uitest.domain.HistoricalEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.*

class HistoricalEventViewModel(
    pager: Pager<Int, HistoricalEvent>
): ViewModel() {

    private val _uiState = MutableStateFlow(ViewState())
    val uiState: StateFlow<ViewState> = _uiState.asStateFlow()

    val historicalEventPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it}
        }
        .cachedIn(viewModelScope)

    fun onSearchTextChanged(text: String) {
        _uiState.update {
            it.copy(
                searchText = text
            )
        }
    }

    fun onSearchClicked() {
        HistoricalEventRemoteMediator.load()
    }
}

data class ViewState(
    val searchText: String = "",
    //val events: List<HistoricalEvent> = emptyList(),
    val error: String? =null
)