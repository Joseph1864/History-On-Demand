package com.example.uitest

import android.app.Application
import com.example.uitest.di.AppModule

class HistoricalEventApp: Application() {
    val appModule = AppModule

    override fun onCreate() {
        super.onCreate()
    }
}