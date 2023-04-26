package com.example.uitest.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.uitest.data.local.HistoricalEventDatabase
import com.example.uitest.data.mappers.toHistoricalEventEntity
import com.example.uitest.domain.HistoricalEvent
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class HistoricalEventRemoteMediator (
    private val historicalEventDb: HistoricalEventDatabase,
    private val historicalEventApi: HistoricalEventApi,
    private val apiKey: String,
    private val keyword: String
    ): RemoteMediator<Int, HistoricalEvent>() {

    override suspend fun load(
        loadType: LoadType,
        state:PagingState<Int, HistoricalEvent>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        0
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val response = historicalEventApi.getHistoricalEvents(
                apiKey = apiKey,
                keyword = keyword,
                offset = loadKey
            )

            historicalEventDb.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    historicalEventDb.dao.clearAll()
                }
                val historicalEventEntities = response.map { it.toHistoricalEventEntity() }
                historicalEventDb.dao.upsertAll(historicalEventEntities)
            }
            MediatorResult.Success(
                endOfPaginationReached = response.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}