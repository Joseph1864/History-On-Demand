package com.example.uitest


class Paginator(
    private val apiKey: String,
    private val keyword: String
) {
    private var offset = 0
    private var isLastPage = false
    private var isLoading = false

    suspend fun getMoreEvents(): List<HistoricalEvent> {
        if (isLoading || isLastPage) return emptyList()

        isLoading = true
        try {
            val response = RetrofitInstance.api.getHistoricalEvents(
                apiKey = apiKey,
                keyword = keyword,
                offset = offset
            )
            val events = response
            if (events.isEmpty()) {
                isLastPage = true
            } else {
                offset += events.size
            }
            return events
        } catch (e: Exception) {
            throw e
        } finally {
            isLoading = false
        }
    }
}