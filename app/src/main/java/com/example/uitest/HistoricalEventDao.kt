package com.example.uitest

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoricalEventDao {
    @Query("SELECT * FROM historicalEvents")
    fun getAll(): List<HistoricalEvent>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(historicalEvents: List<HistoricalEvent>)
}