package com.example.uitest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [HistoricalEventEntity::class],
    version = 1,
)
abstract class HistoricalEventDatabase: RoomDatabase() {

    abstract val historicalEventDao: HistoricalEventDao
}