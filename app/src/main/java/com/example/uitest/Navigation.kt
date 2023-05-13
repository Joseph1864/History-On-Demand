package com.example.uitest

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uitest.screens.HistoricalEventScreen
import com.example.uitest.screens.HistoricalEventViewModel
import com.example.uitest.screens.MainScreen
import com.example.uitest.screens.Screens
import org.koin.androidx.compose.getViewModel

@Composable
fun Navigation() {
    val viewModel = getViewModel<HistoricalEventViewModel>()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
        composable(route = Screens.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screens.HistoricalEventScreen.route) {
            HistoricalEventScreen(viewModel = viewModel)
        }
    }
}