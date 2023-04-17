package com.example.uitest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [HistoricalEventEntity::class],
    version = 1
)
abstract class HistoricalEventDatabase: RoomDatabase() {

    //I sometimes see this as an abstract val or an abstract fun
    //abstract fun getHistoricalEventDao(): HistoricalEventDao
    abstract val dao: HistoricalEventDao

}