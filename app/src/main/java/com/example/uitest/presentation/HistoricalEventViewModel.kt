package com.example.uitest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.uitest.domain.HistoricalEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HistoricalEventViewModel(
    private val pager: Pager<Int, HistoricalEvent>
) : ViewModel() {

    private val _uiState = MutableStateFlow(ViewState())
    val uiState: StateFlow<ViewState> = _uiState.asStateFlow()

    val historicalEventPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it }
        }
        .cachedIn(viewModelScope)

    fun onSearchTextChanged(text: String) {
        _uiState.update {
            it.copy(
                searchText = text
            )
        }
    }

    fun onSearchClicked() = viewModelScope.launch {
        pager.flow.collectLatest { pagingData ->
            _uiState.update {
                it.copy(
                    events = pagingData
                )
            }
        }
    }
}

data class ViewState(
    val searchText: String = "",
    val events: PagingData<HistoricalEvent> = PagingData.empty()
)

