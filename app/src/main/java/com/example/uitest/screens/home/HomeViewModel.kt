package com.example.uitest.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uitest.HistoricalEvent
import com.example.uitest.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ViewState())
    val uiState: StateFlow<ViewState> = _uiState.asStateFlow()

    private val apiKey = "eaui/ZHQXSINNhzEtXfoAQ==Q55LXXAaO8fotgky"

    init {
        getHistoricalEvents()
    }

    fun getHistoricalEvents() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getHistoricalEvents(apiKey)
                _uiState.emit(ViewState(
                    events = response,
                ))
            } catch (e: Exception) {
                _uiState.emit(ViewState(
                    error = e.message,
                ))
            }
        }
    }
}

data class ViewState(
    val events: List<HistoricalEvent> = emptyList(),
    val error: String? =null
)