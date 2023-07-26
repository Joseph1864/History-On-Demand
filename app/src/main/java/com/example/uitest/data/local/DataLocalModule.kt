package com.example.uitest.data.local

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val DataLocalModule = module {
    single<HistoricalEventDatabase> {
        Room.databaseBuilder<HistoricalEventDatabase>(
            context = androidContext(),
            klass = HistoricalEventDatabase::class.java,
            name = "historicalevent.db",
        ).build()
    }

    single<HistoricalEventDao> {
        get<HistoricalEventDatabase>().historicalEventDao
    }
}