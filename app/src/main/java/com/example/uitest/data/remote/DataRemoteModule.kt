package com.example.uitest.data.remote

import com.example.uitest.data.local.HistoricalEventDao
import com.example.uitest.data.local.HistoricalEventDatabase
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val DataRemoteModule = module {

    single<AuthInterceptor> {
        AuthInterceptor()
    }

    single<OkHttpClient> {
        OkHttpClient
            .Builder()
            .addInterceptor(get<AuthInterceptor>())
            .build()
    }

    single<GsonConverterFactory> {
        GsonConverterFactory
            .create()
    }

    single<CoroutineCallAdapterFactory> {
        CoroutineCallAdapterFactory()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com")
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(get<CoroutineCallAdapterFactory>())
            .client(get<OkHttpClient>())
            .build()
    }

    single<HistoricalEventApi> {
        get<Retrofit>().create(HistoricalEventApi::class.java)
    }

    single<HistoricalEventRemoteMediator> {
        HistoricalEventRemoteMediator(
            historicalEventDao = get<HistoricalEventDao>(),
            historicalEventApi = get<HistoricalEventApi>(),
        )
    }
}