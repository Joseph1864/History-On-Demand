package com.example.uitest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HistoricalEventApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.api-ninjas.com/v1/historicalevents/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(HistoricalEventApiService::class.java)

    suspend fun getHistoricalEvents():List<HistoricalEvent> {
        return service.getHistoricalEvents("eaui/ZHQXSINNhzEtXfoAQ==Q55LXXAaO8fotgky").body() ?: emptyList()
    }
}