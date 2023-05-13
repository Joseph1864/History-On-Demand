package com.example.uitest.screens

sealed class Screens(val route: String) {
    object MainScreen : Screens( "main_screen")
    object HistoricalEventScreen: Screens("historical_event_screen")
}
