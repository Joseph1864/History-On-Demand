package com.example.uitest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.uitest.di.AppModule
import com.example.uitest.presentation.HistoricalEventScreen
import com.example.uitest.presentation.HistoricalEventViewModel
import com.example.uitest.presentation.HistoricalEventViewModelFactory
import com.example.uitest.ui.theme.UiTestTheme

class MainActivity : ComponentActivity() {

    //private val homeViewModel by viewModels<HomeViewModel>()
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
                            keyword = "Rome"
                        )
                    )
                    val viewModel = ViewModelProvider(this, viewModelFactory).get(HistoricalEventViewModel::class.java)
                    val historicalEvents = viewModel.historicalEventPagingFlow.collectAsLazyPagingItems()
                    HistoricalEventScreen(historicalEvents = historicalEvents)

                }

//                val uiState by homeViewModel.uiState.collectAsState()
//
//                LazyColumn {
//                    item {
//                        TextField(
//                            value = uiState.searchText,
//                            onValueChange = homeViewModel::onSearchTextChanged,
//                            label = { Text("Enter a Keyword") },
//                            modifier = Modifier.fillMaxWidth()
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        Button(
//                            onClick = homeViewModel::onSearchClicked
//                        ) {
//                            Text(text = "search")
//                        }
//                    }
//                    items(uiState.events.size) {
//                        HistoricalEventCard(uiState.events[it])
//                    }
//                }

            }
        }
    }
}
