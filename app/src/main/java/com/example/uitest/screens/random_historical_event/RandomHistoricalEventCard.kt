package com.example.uitest.screens.random_historical_event

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uitest.domain.HistoricalEvent
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomHistoricalEventCard(event: HistoricalEvent, modifier: Modifier) {
    val eventDate = Calendar.getInstance()
    eventDate.set(
        event.year.toInt(),
        event.month.toInt() -1,
        event.day.toInt()
    )
    val dateFormat = SimpleDateFormat("MMMM d, y G", Locale.getDefault())
    val dateString = dateFormat.format(eventDate.time)


    Card(modifier = Modifier
        .padding(horizontal = 16.dp)
        .fillMaxWidth()
        .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Text(
            modifier = Modifier.padding(6.dp),
            text = dateString,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text(
            modifier = Modifier.padding(6.dp),
            text = event.event,
            fontSize = 22.sp
        )
    }

}