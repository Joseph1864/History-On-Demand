package com.example.uitest

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historicalEvents")
data class HistoricalEvent(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val day: String,
    val event: String,
    val month: String,
    val year: String
)
