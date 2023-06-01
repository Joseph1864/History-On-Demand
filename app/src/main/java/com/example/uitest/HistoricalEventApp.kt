package com.example.uitest

import android.app.Application
import com.example.uitest.data.local.dataLocalModule
import com.example.uitest.data.remote.dataRemoteModule
import com.example.uitest.domain.domainModule
import com.example.uitest.screens.random_historical_event.randomHistoricalEventModule
import com.example.uitest.screens.search_historical_event.searchHistoricalEventModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HistoricalEventApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@HistoricalEventApp)
            modules(
                dataLocalModule,
                dataRemoteModule,
                domainModule,
                randomHistoricalEventModule,
                searchHistoricalEventModule
            )
        }
    }
}