package com.example.uitest.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uitest.R


@Composable
fun MainScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.weight(0.25f))
            Text(
                text = "Welcome to History on Demand!",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)
                    .align(CenterHorizontally),
                textAlign = TextAlign.Center,
                lineHeight = 48.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(R.drawable.globe_books),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            MainScreenButton(
                buttonText = "Search Historical Event",
                painterResource = R.drawable.globe_magnifying_glass
            ) {
                navController.navigate(Screens.SearchHistoricalEventScreen.route)
            }
            Spacer(modifier = Modifier.weight(0.25f))
            MainScreenButton(
                buttonText = "Random Historical Event",
                painterResource = R.drawable.random_event
            ) {
                navController.navigate(Screens.RandomHistoricalEventScreen.route)
            }
            Spacer(modifier = Modifier.weight(0.25f))
        }
    }
}
