package com.example.uitest.data.local

import androidx.paging.PagingSource
import androidx.room.*
import com.example.uitest.data.remote.HistoricalEvent

@Dao
interface HistoricalEventDao {

//    @Query("SELECT * FROM historicalevententity")
//    fun getAll(): List<HistoricalEvent>

    @Upsert
    fun upsertAll(historicalEvents: List<HistoricalEvent>)

    @Query("SELECT * FROM historicalevententity")
    fun pagingSource(): PagingSource<Int, HistoricalEventEntity>

    @Query("DELETE FROM historicalevententity")
    suspend fun clearAll()

}