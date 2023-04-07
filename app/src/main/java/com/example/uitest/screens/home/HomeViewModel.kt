package com.example.uitest.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uitest.RetrofitInstance
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val apiKey = "eaui/ZHQXSINNhzEtXfoAQ==Q55LXXAaO8fotgky"
    var uiState by remember { mutableStateOf<List<String>>(emptyList()) }

    fun fetchData() {
        viewModelScope.launch(Dipatchers.IO) {
            try{
                val response = RetrofitInstance.api.getHistoricalEvents(apiKey)
                uiState = response.map { it.event }
            } catch(e:Exception) {
                println("Error: ")
            }
        }
    }

}