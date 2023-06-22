package com.example.uitest.domain

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class HistoricalEvent(
    var id: Int = 0,
    val day: String,
    val event: String,
    val month: String,
    val year: String
) {
    fun getFormattedDate() : String {
        val eventDate = Calendar.getInstance()
        eventDate.set(
            year.toInt(),
            month.toInt() - 1,
            day.toInt()
        )
        val dateFormat = SimpleDateFormat("MMMM d, y G", Locale.getDefault())
        return dateFormat.format(eventDate.time)
    }
}
