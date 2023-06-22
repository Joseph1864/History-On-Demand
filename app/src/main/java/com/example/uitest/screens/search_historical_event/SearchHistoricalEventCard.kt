package com.example.uitest.screens.search_historical_event

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.uitest.domain.HistoricalEvent
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchHistoricalEventCard(event: HistoricalEvent, dateString: String) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(6.dp),
            text = dateString,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.padding(6.dp),
            text = event.event
        )
    }

}