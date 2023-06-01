package com.example.uitest.screens.random_historical_event

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun RandomHistoricalEventScreen(
    viewModel: RandomHistoricalEventViewModel,
) {

    val uiState by viewModel.uiState.collectAsState()
    Content(
        viewState = uiState,
        onGenerateEventClicked = viewModel::generateEventClicked,
    )
}

@Composable
private fun Content(
    viewState: RandomHistoricalEventViewState,
    onGenerateEventClicked: () -> Unit,
) = ConstraintLayout(
    modifier = Modifier
        .fillMaxSize()
) {

    val (error, loading, content, generateButton) = createRefs()

    when(viewState) {
        RandomHistoricalEventViewState.Error ->
            Text(
                modifier = Modifier.constrainAs(error) {
                    centerTo(parent)
                },
                text = "No internet connection :(",
                fontSize = 24.sp
            )
        RandomHistoricalEventViewState.Loading ->
            CircularProgressIndicator(
            modifier = Modifier.constrainAs(loading) {
                centerTo(parent)
            }
        )
        is RandomHistoricalEventViewState.Content ->
            RandomHistoricalEventCard(
                event = viewState.event,
                modifier = Modifier.constrainAs(content) {
                    centerHorizontallyTo(parent)
                    top.linkTo(parent.top, margin = 64.dp)
                }
            )
    }
    Button(
        onClick = onGenerateEventClicked,
        modifier = Modifier
            .constrainAs(generateButton) {
                centerHorizontallyTo(parent)
                bottom.linkTo(parent.bottom, margin = 64.dp)
            }
            .width(300.dp)
            .height(50.dp)
    ) {
        Text(
            text = "Random Event!",
            fontSize = 24.sp
        )
    }
}