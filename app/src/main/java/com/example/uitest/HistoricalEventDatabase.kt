package com.example.uitest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [HistoricalEvent::class], version = 1)
abstract class HistoricalEventDatabase: RoomDatabase() {
    abstract fun historicalEventDao(): HistoricalEventDao

    companion object {
        @Volatile
        private var INSTANCE: HistoricalEventDatabase? = null

        fun getDatabase(context: Context): HistoricalEventDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HistoricalEventDatabase::class.java,
                    "historical_event_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}