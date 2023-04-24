package com.example.uitest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.Pager
import com.example.uitest.data.local.HistoricalEventEntity
import com.example.uitest.domain.HistoricalEvent

@Suppress("UNCHECKED_CAST")
class HistoricalEventViewModelFactory(
    private val pager: Pager<Int, HistoricalEvent>
) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoricalEventViewModel::class.java)) {
            return HistoricalEventViewModel(pager) as T
        }
        throw IllegalArgumentException("ViewModel not found")
    }

}