package com.example.uitest.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uitest.data.remote.HistoricalEvent
import com.example.uitest.data.Paginator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val apiKey = "eaui/ZHQXSINNhzEtXfoAQ==Q55LXXAaO8fotgky"

    private val _uiState = MutableStateFlow(ViewState())
    val uiState: StateFlow<ViewState> = _uiState.asStateFlow()

    private val paginator = Paginator(apiKey)

    private fun getHistoricalEvents() {
        viewModelScope.launch {
            try {
                val events = paginator.getMoreEvents(uiState.value.searchText)
                _uiState.emit(ViewState(
                    events = events,
                ))
            } catch (e: Exception) {
                _uiState.emit(ViewState(
                    error = e.message,
                ))
            }
        }
    }

    fun onSearchTextChanged(text: String) {
        _uiState.update {
            it.copy(
                searchText = text
            )
        }
    }

    fun onSearchClicked() {
        getHistoricalEvents()
    }
}

data class ViewState(
    val searchText: String = "",
    val events: List<HistoricalEvent> = emptyList(),
    val error: String? =null
)