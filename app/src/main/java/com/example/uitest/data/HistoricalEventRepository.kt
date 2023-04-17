package com.example.uitest.data

import com.example.uitest.data.local.HistoricalEventDao
import com.example.uitest.data.remote.RetrofitInstance
import com.example.uitest.data.remote.HistoricalEvent
import com.example.uitest.data.remote.HistoricalEventApiService

class HistoricalEventRepository(
    private val historicalEventDao: HistoricalEventDao,
    private val historicalEventApiService: HistoricalEventApiService,
    private val apiKey: String
    ) {
    private var offset = 0
    private var isLastPage = false
    private var keyword = ""

    suspend fun getHistoricalEvents(keyword: String): List<HistoricalEvent> {
        if (keyword != this.keyword) {
            offset = 0
            isLastPage = false
        }
        if (isLastPage) return emptyList()

        val cachedHistoricalEvents = historicalEventDao.getAll()
        return if (cachedHistoricalEvents.isNotEmpty()) {
            cachedHistoricalEvents
        } else {
            val response = RetrofitInstance.api.getHistoricalEvents(
                apiKey = apiKey,
                keyword = keyword,
                offset = offset
            )
            if (response.isEmpty()) {
                isLastPage = true
            } else {
                offset += response.size
            }
            historicalEventDao.insertAll(response)
            response
        }
    }
}