package com.example.uitest

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uitest.screens.searchHistoricalEvent.HistoricalEventScreen
import com.example.uitest.screens.searchHistoricalEvent.SearchHistoricalEventViewModel
import com.example.uitest.screens.main.MainScreen
import com.example.uitest.screens.randomHistoricalEvent.RandomHistoricalEventScreen
import com.example.uitest.screens.randomHistoricalEvent.RandomHistoricalEventViewModel
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