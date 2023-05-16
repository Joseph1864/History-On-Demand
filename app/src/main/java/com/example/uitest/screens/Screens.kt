package com.example.uitest.screens

sealed class Screens(val route: String) {
    object MainScreen : Screens("main_screen")
    object SearchHistoricalEventScreen : Screens("search_historical_event_screen")
    object RandomHistoricalEventScreen : Screens("random_historical_event_screen")
}
