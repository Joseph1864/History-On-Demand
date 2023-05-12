package com.example.uitest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.uitest.data.remote.HistoricalEventApi
//import com.example.uitest.di.AppModule
import com.example.uitest.presentation.HistoricalEventScreen
import com.example.uitest.presentation.HistoricalEventViewModel
import com.example.uitest.presentation.HistoricalEventViewModelFactory
import com.example.uitest.ui.theme.UiTestTheme
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {

    private val historicalEventApi by inject<HistoricalEventApi>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UiTestTheme {
                val viewModel = getViewModel<HistoricalEventViewModel>()
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    HistoricalEventScreen(viewModel)
                }
            }
        }
    }
}
