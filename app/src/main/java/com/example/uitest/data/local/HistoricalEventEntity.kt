package com.example.uitest.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "historicalevententity")
data class HistoricalEventEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val day: String,
    val event: String,
    val month: String,
    val year: String
)
