package com.example.uitest.data

import com.example.uitest.data.remote.RetrofitInstance
import com.example.uitest.domain.HistoricalEvent


class Paginator(
    private val apiKey: String
) {
    private var offset = 0
    private var isLastPage = false
    private var keyword = ""

    suspend fun getMoreEvents(keyword: String): List<HistoricalEvent> {
        if (keyword != this.keyword) {
            offset = 0
            isLastPage = false
        }
        if (isLastPage) return emptyList()

        try {
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
            return response
        } catch (e: Exception) {
            println(e.message)
        }
        return emptyList()
    }
}