package com.example.uitest.data.local

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataLocalModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            HistoricalEventDatabase::class.java,
            "historicalevent.db"
        ).build()
    }

    single {
        get<HistoricalEventDatabase>().dao
    }

}