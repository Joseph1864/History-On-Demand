package com.example.uitest.data.local

import androidx.paging.PagingSource
import androidx.room.*
import com.example.uitest.domain.HistoricalEvent

@Dao
interface HistoricalEventDao {

    @Upsert
    fun upsertAll(historicalEvents: List<HistoricalEventEntity>)

    @Query("SELECT * FROM historicalevententity")
    fun pagingSource(): PagingSource<Int, HistoricalEvent>

    @Query("DELETE FROM historicalevententity")
    suspend fun clearAll()

}