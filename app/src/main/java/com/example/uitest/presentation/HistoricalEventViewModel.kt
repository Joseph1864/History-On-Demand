package com.example.uitest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.uitest.data.local.HistoricalEventEntity
import com.example.uitest.data.mappers.toHistoricalEvent
import kotlinx.coroutines.flow.map

class HistoricalEventViewModel(
    pager: Pager<Int, HistoricalEventEntity >
): ViewModel() {

    val historicalEventPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toHistoricalEvent() }
        }
        .cachedIn(viewModelScope)

}