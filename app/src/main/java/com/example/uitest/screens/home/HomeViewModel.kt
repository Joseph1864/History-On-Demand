package com.example.uitest.screens.home

//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import androidx.paging.Pager
//import androidx.paging.cachedIn
//import androidx.paging.map
//import com.example.uitest.domain.HistoricalEvent
//import com.example.uitest.data.local.HistoricalEventEntity
//import com.example.uitest.data.mappers.toHistoricalEvent
//import kotlinx.coroutines.flow.*
//import kotlinx.coroutines.launch
//
////class HomeViewModel: ViewModel() {
//    private val apiKey = "eaui/ZHQXSINNhzEtXfoAQ==Q55LXXAaO8fotgky"
//
//    private val _uiState = MutableStateFlow(ViewState())
//    val uiState: StateFlow<ViewState> = _uiState.asStateFlow()
//
//    private val paginator = Paginator(apiKey)
//
//    private fun getHistoricalEvents() {
//        viewModelScope.launch {
//            try {
//                val events = paginator.getMoreEvents(uiState.value.searchText)
//                _uiState.emit(ViewState(
//                    events = events,
//                ))
//            } catch (e: Exception) {
//                _uiState.emit(ViewState(
//                    error = e.message,
//                ))
//            }
//        }
//    }
//
//    fun onSearchTextChanged(text: String) {
//        _uiState.update {
//            it.copy(
//                searchText = text
//            )
//        }
//    }
//
//    fun onSearchClicked() {
//        getHistoricalEvents()
//    }
//}
//
//data class ViewState(
//    val searchText: String = "",
//    val events: List<HistoricalEvent> = emptyList(),
//    val error: String? =null
//)