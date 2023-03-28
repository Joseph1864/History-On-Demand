package com.example.uitest

import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HistoricalEventApi {
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY})
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.api-ninjas.com/v1/historicalevents/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    private val service = retrofit.create(HistoricalEventApiService::class.java)

    suspend fun getHistoricalEvents():List<HistoricalEvent> {
        return service.getHistoricalEvents("eaui/ZHQXSINNhzEtXfoAQ==Q55LXXAaO8fotgky").body() ?: emptyList()
    }
}