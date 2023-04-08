package com.example.uitest

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface HistoricalEventApiService {
    @GET("/v1/historicalevents")
    suspend fun getHistoricalEvents(
        @Header("X-Api-Key") apiKey:String,
        @Query("text") keyword: String,
        @Query("offset") offset: Int
    ): List<HistoricalEvent>
}