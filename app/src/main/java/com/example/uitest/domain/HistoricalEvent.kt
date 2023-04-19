package com.example.uitest.domain

//Is initializing the "id" property a bad idea? I need it when I convert the api response
//HistoricalEvent to HistoricalEventEntity for Room
data class HistoricalEvent(
    var id: Int = 0,
    val day: String,
    val event: String,
    val month: String,
    val year: String
)
