
package com.example.uitest.data.remote

import com.example.uitest.domain.HistoricalEvent
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface HistoricalEventApi {
    @GET("/v1/historicalevents")
    suspend fun getHistoricalEvents(
        @Query("text") keyword: String,
        @Query("offset") offset: Int
    ): List<HistoricalEvent>
}