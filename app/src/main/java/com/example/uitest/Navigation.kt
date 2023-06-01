package com.example.uitest

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uitest.screens.search_historical_event.HistoricalEventScreen
import com.example.uitest.screens.search_historical_event.SearchHistoricalEventViewModel
import com.example.uitest.screens.main.MainScreen
import com.example.uitest.screens.random_historical_event.RandomHistoricalEventScreen
import com.example.uitest.screens.random_historical_event.RandomHistoricalEventViewModel
import com.example.uitest.screens.Screens
import org.koin.androidx.compose.getViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
        composable(route = Screens.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screens.SearchHistoricalEventScreen.route) {
            val searchViewModel = getViewModel<SearchHistoricalEventViewModel>()
            HistoricalEventScreen(viewModel = searchViewModel)
        }
        composable(route = Screens.RandomHistoricalEventScreen.route) {
            val randomViewModel = getViewModel<RandomHistoricalEventViewModel>()
            RandomHistoricalEventScreen(randomViewModel)
        }
    }
}