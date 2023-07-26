package com.example.uitest.data.local

import androidx.paging.PagingSource
import androidx.room.*
import com.example.uitest.domain.HistoricalEvent

@Dao
interface HistoricalEventDao {

    @Upsert
    suspend fun upsertAll(historicalEvents: List<HistoricalEventEntity>)

    @Insert
    suspend fun insertAll(historicalEvents: List<HistoricalEventEntity>)

    @Transaction
    suspend fun replaceEvents(historicalEvents: List<HistoricalEventEntity>) {
        clearAll()
        upsertAll(historicalEvents)
    }
    @Query("SELECT * FROM historical_events")
    fun pagingSource(): PagingSource<Int, HistoricalEvent>

    @Query("DELETE FROM historical_events")
    suspend fun clearAll()

}