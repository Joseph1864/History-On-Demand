package com.example.uitest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.LazyPagingItems
import androidx.paging.map
import com.example.uitest.domain.HistoricalEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.*
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
        pager.flow.collectLatest { lazyPagingItems->
            _uiState.update {
                it.copy(
                    events = LazyPagingItems(pager.flow)
                )
            }
        }
    }
}

data class ViewState(
    val searchText: String = "",
    val events: LazyPagingItems<HistoricalEvent> = LazyPagingItems(emptyList())
)