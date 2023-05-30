package com.example.uitest.screens.searchHistoricalEvent

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun HistoricalEventScreen(
    viewModel : SearchHistoricalEventViewModel
) {

    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    val historicalEvents = viewModel.historicalEventPagingFlow.collectAsLazyPagingItems()

    LaunchedEffect(key1 = historicalEvents.loadState) {
        if (historicalEvents.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (historicalEvents.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = uiState.searchText,
            onValueChange = viewModel::onSearchTextChanged,
            label = { Text("Enter a Keyword") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp)
                .clip(RoundedCornerShape(percent = 50)),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        if (historicalEvents.loadState.refresh is LoadState.Error) {
            Text(
                text = "No internet connection :(",
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else if (historicalEvents.itemCount == 0) {
            Text(
                text = "No results found",
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else if (historicalEvents.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(historicalEvents.itemCount) { index ->
                    val historicalEvent = historicalEvents[index]
                    if (historicalEvent != null) {
                        SearchHistoricalEventCard(historicalEvent)
                    }
                }
                item {
                    if (historicalEvents.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}