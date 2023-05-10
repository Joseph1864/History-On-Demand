package com.example.uitest.di

import android.content.Context
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
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//@OptIn(ExperimentalPagingApi::class)
//object AppModule {
//    fun provideHistoricalEventDatabase(context: Context): HistoricalEventDatabase {
//        return Room.databaseBuilder(
//            context,
//            HistoricalEventDatabase::class.java,
//            "historicalevent.db"
//        ).build()
//    }
//
//    private val client = OkHttpClient
//        .Builder()
//        .addInterceptor(AuthInterceptor())
//        .build()
//
//    fun provideHistoricalEventApi(): HistoricalEventApi {
//        return Retrofit.Builder()
//            .baseUrl("https://api.api-ninjas.com")
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
//            .client(client)
//            .build()
//            .create(HistoricalEventApi::class.java)
//    }
//
//    fun provideRemoteMediator(
//        historicalEventDb: HistoricalEventDatabase,
//        historicalEventApi: HistoricalEventApi,
//    ) = HistoricalEventRemoteMediator(
//        historicalEventDb = historicalEventDb,
//        historicalEventApi = historicalEventApi,
//    )
//
//    fun provideHistoricalEventPager(
//        remoteMediator: HistoricalEventRemoteMediator,
//        historicalEventDb: HistoricalEventDatabase,
//    ): Pager<Int, HistoricalEvent> {
//        return Pager(
//            config = PagingConfig(pageSize = 10),
//            remoteMediator = remoteMediator,
//            pagingSourceFactory = {
//                historicalEventDb.dao.pagingSource()
//            }
//        )
//    }
//
//    fun provideRepository(
//        pager: Pager<Int, HistoricalEvent>,
//        remoteMediator: HistoricalEventRemoteMediator,
//    ) = HistoricalEventRepository(
//        pager = pager,
//        remoteMediator = remoteMediator,
//    )
//}