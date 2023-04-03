package com.example.uitest

import retrofit2.http.GET
import retrofit2.http.Header

interface HistoricalEventApiService {
    @GET("/v1/historicalevents?text=roman empire")
    suspend fun getHistoricalEvents(
        @Header("X-Api-Key") apiKey:String
    ): List<HistoricalEvent>
}