package com.example.uitest.screens.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uitest.domain.HistoricalEvent


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
            modifier = Modifier.padding(bottom = 8.dp),
            text = "${event.day} ${event.month} ${event.year}"
        )
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = event.event
        )
    }

}