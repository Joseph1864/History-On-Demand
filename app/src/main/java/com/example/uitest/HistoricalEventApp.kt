package com.example.uitest

import android.app.Application
//import com.example.uitest.di.AppModule
import com.example.uitest.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HistoricalEventApp: Application() {
    //val appModule = AppModule

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@HistoricalEventApp)
            modules(appModules)
        }
    }
}