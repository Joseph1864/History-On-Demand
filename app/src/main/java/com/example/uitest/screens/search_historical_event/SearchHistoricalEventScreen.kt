package com.example.uitest.screens.search_historical_event

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@Composable
fun HistoricalEventScreen(
    viewModel : SearchHistoricalEventViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    val historicalEvents = viewModel.historicalEventPagingFlow.collectAsLazyPagingItems()


    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = uiState.searchText,
            onValueChange = viewModel::onSearchTextChanged,
            label = { Text("Enter a Keyword") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp)
                .clip(RoundedCornerShape(percent = 50))
                .horizontalScroll(rememberScrollState()),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )
        if (uiState.searchText == "") {
            Text(
                text = "Enter a keyword!",
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else if (uiState.searchText != "" && historicalEvents.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else if (historicalEvents.loadState.refresh is LoadState.Error) {
            when ((historicalEvents.loadState.refresh as LoadState.Error).error)  {
                is UnknownHostException, is ConnectException, is SocketTimeoutException ->
                    Text(
                        text = "No internet connection :(",
                        fontSize = 24.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                else ->
                    Text(
                        text = "Something went wrong :O",
                        fontSize = 24.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
            }
        } else if (historicalEvents.itemCount == 0 && uiState.searchText != "") {
            Text(
                text = "No results found",
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
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
                        SearchHistoricalEventCard(historicalEvent, viewModel = viewModel)
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