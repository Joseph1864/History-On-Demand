package com.example.uitest.data.mappers

import com.example.uitest.data.local.HistoricalEventEntity
import com.example.uitest.domain.HistoricalEvent

fun HistoricalEvent.toHistoricalEventEntity(): HistoricalEventEntity {
    return HistoricalEventEntity(
        id = id,
        day = day,
        event = event,
        month = month,
        year = year
    )
}

fun HistoricalEventEntity.toHistoricalEvent(): HistoricalEvent {
    return HistoricalEvent(
        id = id,
        day = day,
        event = event,
        month = month,
        year = year
    )
}