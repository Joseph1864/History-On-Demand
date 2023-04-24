package com.example.uitest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.uitest.data.local.HistoricalEventDatabase
import com.example.uitest.data.remote.HistoricalEventApi
import com.example.uitest.di.AppModule
import com.example.uitest.domain.HistoricalEvent
import com.example.uitest.presentation.HistoricalEventScreen
import com.example.uitest.presentation.HistoricalEventViewModel
import com.example.uitest.presentation.HistoricalEventViewModelFactory
//import com.example.uitest.screens.home.HomeViewModel
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
                            historicalEventApi = historicalEventApi
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

//Just ignore all this, it was just a quick little test to have a text bar the disappears when
//you input text and hit a button, but now that you're looking here, should I have it disappear when
//you hit the button or would it be easier to navigate to a new screen?
//@Composable
//fun HomeScreen() {
//    var showInput by remember { mutableStateOf(true) }
//    var keyword by remember { mutableStateOf("") }
//    var itemList by remember { mutableStateOf(listOf<String>()) }
//
//    //itemList = listOf("one", "two", "three", "four", "five", "six")
//    if (showInput) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            TextField(
//                value = keyword,
//                singleLine = true,
//                onValueChange = { keyword = it },
//                modifier = Modifier.fillMaxWidth()
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            Button(
//                onClick = {
//                    showInput = false
//                },
//                modifier = Modifier.align(Alignment.End)
//            ) {
//                Text("History Time!")
//            }
//        }
//    } else {
//        LazyColumn(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            itemsIndexed(itemList) { index, item ->
//                HistoricalEventCard(event = item)
//            }
//        }
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoricalEventCard(event: HistoricalEvent) {
    Card(modifier = Modifier
        .padding(32.dp, 16.dp)
        .fillMaxWidth()
        .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "${event.day} ${event.month} ${event.year}"
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = event.event
        )
    }

}