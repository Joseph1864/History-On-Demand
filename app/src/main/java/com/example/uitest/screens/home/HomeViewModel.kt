package com.example.uitest.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uitest.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<List<String>>(emptyList())
    val uiState: StateFlow<List<String>> = _uiState.asStateFlow()

    private val apiKey = "eaui/ZHQXSINNhzEtXfoAQ==Q55LXXAaO8fotgky"

    fun getHistoricalEvents() {
        viewModelScope.launch {
            val response = RetrofitInstance.api.getHistoricalEvents(apiKey)
            try {
                _uiState.emit(response.map { it.event })
            } catch (e: Exception) {
                println("Error: $e")
            }
        }
    }

}