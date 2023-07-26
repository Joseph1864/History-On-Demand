package com.example.uitest.data.mappers

import com.example.uitest.data.local.HistoricalEventEntity
import com.example.uitest.domain.HistoricalEvent


fun List<HistoricalEvent>.toHistoricalEventEntities() = map {
    it.toHistoricalEventEntity()
}
fun HistoricalEvent.toHistoricalEventEntity() = HistoricalEventEntity(
    day = day,
    event = event,
    month = month,
    year = year,
)

fun HistoricalEventEntity.toHistoricalEvent() = HistoricalEvent(
    day = day,
    event = event,
    month = month,
    year = year,
)