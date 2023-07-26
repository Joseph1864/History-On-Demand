package com.example.uitest.domain

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.example.uitest.data.local.HistoricalEventDao
import com.example.uitest.data.local.HistoricalEventDatabase
import com.example.uitest.data.remote.HistoricalEventRemoteMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HistoricalEventRepository(
    private val historicalEventDao: HistoricalEventDao,
    private val remoteMediator: HistoricalEventRemoteMediator,
    private val pager: Pager<Int, HistoricalEvent>
) {

    fun events(keyword: String): Flow<PagingData<HistoricalEvent>> {
        remoteMediator.setKeyword(keyword)
        return pager
            .flow
            .map { pagingData -> pagingData.map { it } }
    }

    suspend fun clearCache() =  historicalEventDao.clearAll()
}