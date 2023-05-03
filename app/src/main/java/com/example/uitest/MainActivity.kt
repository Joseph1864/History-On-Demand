package com.example.uitest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.uitest.di.AppModule
import com.example.uitest.presentation.HistoricalEventScreen
import com.example.uitest.presentation.HistoricalEventViewModel
import com.example.uitest.presentation.HistoricalEventViewModelFactory
import com.example.uitest.ui.theme.UiTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UiTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val historicalEventApp = application as HistoricalEventApp
                    val appModule = historicalEventApp.appModule
                    val context = applicationContext


                    val historicalEventDb = appModule.provideHistoricalEventDatabase(context = context)
                    val historicalEventApi = appModule.provideHistoricalEventApi()

                    val viewModelFactory = HistoricalEventViewModelFactory(
                        pager = AppModule.provideHistoricalEventPager(
                            historicalEventDb = historicalEventDb,
                            historicalEventApi = historicalEventApi,
                            keyword = "Roman"
                        )
                    )
                    val viewModel = ViewModelProvider(this, viewModelFactory)[HistoricalEventViewModel::class.java]
                    HistoricalEventScreen(viewModel)

                }
            }
        }
    }
}
