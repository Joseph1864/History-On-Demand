package com.example.uitest.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uitest.domain.HistoricalEvent
import kotlinx.coroutines.launch


@Composable
fun RandomHistoricalEventScreen(
    viewModel: RandomHistoricalEventViewModel
) {

    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = {
            var event = uiState.event
        },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Generate an event!")
        }
        Spacer(modifier = Modifier.weight(1f))
        HistoricalEventCard(event = event)

    }
}