package com.example.uitest.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun HistoricalEventScreen(
    viewModel: HistoricalEventViewModel
) {

    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    val historicalEvents = viewModel.historicalEventPagingFlow.collectAsLazyPagingItems()

    LaunchedEffect(key1 = historicalEvents.loadState) {
        if(historicalEvents.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (historicalEvents.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if(historicalEvents.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                        TextField(
                            value = uiState.searchText,
                            onValueChange = viewModel::onSearchTextChanged,
                            label = { Text("Enter a Keyword") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = viewModel::onSearchClicked
                        ) {
                            Text(text = "search")
                        }
                    }
                items(historicalEvents.itemCount) { index ->
                    val historicalEvent = historicalEvents[index]
                    if (historicalEvent != null) {
                        HistoricalEventCard(historicalEvent)
                    }
                }
                item {
                    if(historicalEvents.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}