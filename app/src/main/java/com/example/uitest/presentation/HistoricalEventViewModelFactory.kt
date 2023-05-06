package com.example.uitest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.uitest.data.remote.HistoricalEventRepository

@Suppress("UNCHECKED_CAST")
class HistoricalEventViewModelFactory(
    private val repository: HistoricalEventRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoricalEventViewModel::class.java)) {
            return HistoricalEventViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel not found")
    }

}