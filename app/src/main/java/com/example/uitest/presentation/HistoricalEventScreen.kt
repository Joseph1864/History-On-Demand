package com.example.uitest.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.uitest.domain.HistoricalEvent

@Composable
fun HistoricalEventScreen(
    historicalEvents: LazyPagingItems<HistoricalEvent>
) {
    val context = LocalContext.current
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
                items(historicalEvents) { historicalEvent ->
                    if (historicalEvent != null) {
                        HistoricalEventCard(
                            event = historicalEvent
                        )
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