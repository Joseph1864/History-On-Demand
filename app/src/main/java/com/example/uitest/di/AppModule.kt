package com.example.uitest.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.uitest.data.local.HistoricalEventDatabase
import com.example.uitest.data.remote.AuthInterceptor
import com.example.uitest.data.remote.HistoricalEventApi
import com.example.uitest.data.remote.HistoricalEventRemoteMediator
import com.example.uitest.data.remote.HistoricalEventRepository
import com.example.uitest.domain.HistoricalEvent
import com.example.uitest.screens.HistoricalEventViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalPagingApi::class)
val appModules = module {
    viewModel {
        HistoricalEventViewModel(get<HistoricalEventRepository>())
    }
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
        Room.databaseBuilder(
            androidContext(),
            HistoricalEventDatabase::class.java,
            "historicalevent.db"
        ).build()
    }
    single {
        HistoricalEventRemoteMediator(
            historicalEventDb = get<HistoricalEventDatabase>(),
            historicalEventApi = get<HistoricalEventApi>()
        )
    }
    single {
        Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = get<HistoricalEventRemoteMediator>(),
            pagingSourceFactory = {
                get<HistoricalEventDatabase>().dao.pagingSource()
            }
        )
    }
    single {
        HistoricalEventRepository(
            pager = get<Pager<Int, HistoricalEvent>>(),
            remoteMediator = get<HistoricalEventRemoteMediator>()
        )
    }
}