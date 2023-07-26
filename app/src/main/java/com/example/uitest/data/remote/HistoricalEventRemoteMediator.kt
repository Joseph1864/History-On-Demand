package com.example.uitest.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.uitest.data.local.HistoricalEventDao
import com.example.uitest.data.mappers.toHistoricalEventEntities
import com.example.uitest.domain.HistoricalEvent
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class HistoricalEventRemoteMediator(
    private val historicalEventDao: HistoricalEventDao,
    private val historicalEventApi: HistoricalEventApi,
) : RemoteMediator<Int, HistoricalEvent>() {

    private var offset = 0
    private var keyword = ""

    fun setKeyword(keyword: String) {
        this.keyword = keyword
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, HistoricalEvent>
    ): MediatorResult = try {
        when (loadType) {
            LoadType.REFRESH -> refresh()
            LoadType.PREPEND -> prepend()
            LoadType.APPEND -> append(state)
        }
        val events = historicalEventApi.getHistoricalEvents(
            keyword = keyword,
            offset = offset,
        )
        if (loadType == LoadType.REFRESH) {
            replaceEvents(events)
        } else {
            updateEvents(events)
        }

        MediatorResult.Success(
            endOfPaginationReached = events.isEmpty(),
        )
    } catch (e: IOException) {
        MediatorResult.Error(e)
    } catch (e: HttpException) {
        MediatorResult.Error(e)
    }

    private fun refresh() {
        offset = 0
    }

    private fun prepend() = MediatorResult.Success(
        endOfPaginationReached = true
    )

    private fun append(state: PagingState<Int, HistoricalEvent>) {
        val lastItem = state.lastItemOrNull()
        if (lastItem == null) {
            offset = 0
        } else {
            offset += 10
        }
    }

    private suspend fun updateEvents(
        events: List<HistoricalEvent>,
    ) = historicalEventDao.upsertAll(events.toHistoricalEventEntities())

    private suspend fun replaceEvents(
        events: List<HistoricalEvent>,
    ) = historicalEventDao.replaceEvents(events.toHistoricalEventEntities())
}