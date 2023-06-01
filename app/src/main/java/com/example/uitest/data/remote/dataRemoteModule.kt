package com.example.uitest.data.remote

import com.example.uitest.data.local.HistoricalEventDatabase
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataRemoteModule = module {
    single {
        AuthInterceptor()
    }
    single {
        OkHttpClient
            .Builder()
            .addInterceptor(get<AuthInterceptor>())
            .build()
    }
    single {
        GsonConverterFactory
            .create()
    }
    single {
        CoroutineCallAdapterFactory()
    }
    single {
        Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com")
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(get<CoroutineCallAdapterFactory>())
            .client(get<OkHttpClient>())
            .build()
            .create(HistoricalEventApi::class.java)
    }
    single {
        HistoricalEventRemoteMediator(
            historicalEventDb = get<HistoricalEventDatabase>(),
            historicalEventApi = get<HistoricalEventApi>()
        )
    }
}