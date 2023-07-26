package com.example.uitest.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "historical_events")
data class HistoricalEventEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = -1,
    val day: String,
    val event: String,
    val month: String,
    val year: String
)
