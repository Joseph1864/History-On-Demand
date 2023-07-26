package com.example.uitest.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.uitest.data.local.HistoricalEventDao
import com.example.uitest.data.local.HistoricalEventDatabase
import com.example.uitest.data.remote.HistoricalEventRemoteMediator
import org.koin.dsl.module

@OptIn(ExperimentalPagingApi::class)
val domainModule = module {
    single {
        HistoricalEventRepository(
            historicalEventDao = get<HistoricalEventDao>(),
            pager = get<Pager<Int, HistoricalEvent>>(),
            remoteMediator = get<HistoricalEventRemoteMediator>(),
        )
    }
    single {
        Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = get<HistoricalEventRemoteMediator>(),
            pagingSourceFactory = {
                get<HistoricalEventDao>().pagingSource()
            }
        )
    }
}