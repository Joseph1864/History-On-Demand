package com.example.uitest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uitest.ui.theme.UiTestTheme
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UiTestTheme {
                val apiKey = "eaui/ZHQXSINNhzEtXfoAQ==Q55LXXAaO8fotgky"
                var uiState by remember { mutableStateOf<List<String>>(emptyList())}

                LaunchedEffect(Dispatchers.IO){
                    val response = RetrofitInstance.api.getHistoricalEvents(apiKey)
                    try {
                        uiState = response.map {it.event}
                    } catch (e: Exception){
                        println("Error: ")
                    }

                }
                LazyColumn {
                    items(uiState.size) {
                        Text(
                            text = uiState[it]
                        )
                    }
                }

            }
        }
    }
}

//Just ignore all this, it was just a quick little test to have a text bar the disappears when
//you input text and hit a button, but now that you're looking here, should I have it dissapear when
//you hit the button or would it be easier to navigate to a new screen?
@Composable
fun HomeScreen() {
    var showInput by remember { mutableStateOf(true) }
    var keyword by remember { mutableStateOf("") }
    var itemList by remember { mutableStateOf(listOf<String>()) }

    //itemList = listOf("one", "two", "three", "four", "five", "six")
    if (showInput) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TextField(
                value = keyword,
                singleLine = true,
                onValueChange = { keyword = it },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    showInput = false
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("History Time!")
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(itemList) { index, item ->
                HistoricalEventCard(historicalEvent = item)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoricalEventCard(historicalEvent: String) {
    Card(modifier = Modifier
        .padding(8.dp, 8.dp)
        .fillMaxWidth()
        .height(1000.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Text(text = historicalEvent)
    }

}