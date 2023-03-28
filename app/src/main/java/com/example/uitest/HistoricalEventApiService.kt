package com.example.uitest

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HistoricalEventApiService {
    @GET("{keyword}")
    suspend fun getHistoricalEvents(
        @Query("X-Api-Key") apiKey:String
    ): Response<List<HistoricalEvent>>
}