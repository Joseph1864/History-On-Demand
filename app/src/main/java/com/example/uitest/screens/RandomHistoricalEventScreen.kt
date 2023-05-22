package com.example.uitest.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RandomHistoricalEventScreen(
    viewModel: RandomHistoricalEventViewModel,
) {

    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        RandomHistoricalEventViewState.Loading -> Loading()
        is RandomHistoricalEventViewState.Content -> Content(
            viewState = uiState as RandomHistoricalEventViewState.Content,
            onGenerateEventClicked = viewModel::generateEventClicked
        )
    }
}

@Composable
private fun Loading() = Box(
    modifier = Modifier.fillMaxSize()
) {
    CircularProgressIndicator()
}

@Composable
private fun Content(
    viewState: RandomHistoricalEventViewState.Content,
    onGenerateEventClicked: () -> Unit,
) = Column(
    verticalArrangement = Arrangement.Center,
    modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
) {
    Spacer(modifier = Modifier.weight(1f))
    Button(
        onClick = onGenerateEventClicked,
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
    ) {
        Text(text = "Generate an event!")
    }
    Spacer(modifier = Modifier.weight(1f))
    HistoricalEventCard(event = viewState.event)
}