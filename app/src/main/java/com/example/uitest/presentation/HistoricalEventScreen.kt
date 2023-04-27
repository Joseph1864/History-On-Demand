package com.example.uitest.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.uitest.domain.HistoricalEvent

@Composable
fun HistoricalEventScreen(
    viewModel: HistoricalEventViewModel
) {

    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(key1 = iuState.events.loadState) {
        if(iuState.events.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (iuState.events.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if(uiState.events.loadState.refresh is LoadState.Loading) {
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
                items(iuState.events) { historicalEvent ->
                    if (historicalEvent != null) {
                        HistoricalEventCard(
                            event = historicalEvent
                        )
                    }
                }
                item {
                    if(iuState.events.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }

}