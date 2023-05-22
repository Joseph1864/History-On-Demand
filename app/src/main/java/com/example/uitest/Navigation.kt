package com.example.uitest

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uitest.screens.HistoricalEventScreen
import com.example.uitest.screens.SearchHistoricalEventViewModel
import com.example.uitest.screens.MainScreen
import com.example.uitest.screens.RandomHistoricalEventScreen
import com.example.uitest.screens.RandomHistoricalEventViewModel
import com.example.uitest.screens.Screens
import org.koin.androidx.compose.getViewModel

@Composable
fun Navigation() {
    val searchViewModel = getViewModel<SearchHistoricalEventViewModel>()
    val randomViewModel = getViewModel<RandomHistoricalEventViewModel>()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
        composable(route = Screens.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screens.SearchHistoricalEventScreen.route) {
            HistoricalEventScreen(viewModel = searchViewModel)
        }
        composable(route = Screens.RandomHistoricalEventScreen.route) {
            RandomHistoricalEventScreen(randomViewModel)
        }
    }
}