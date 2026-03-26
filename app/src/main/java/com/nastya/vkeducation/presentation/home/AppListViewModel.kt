package com.nastya.vkeducation.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastya.vkeducation.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AppListViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {
    private val _state: MutableStateFlow<AppListState> = MutableStateFlow(AppListState.Loading)
    val state = _state.asStateFlow()

    private val _event = Channel<AppListEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    init {
        loadCards()
    }

    private fun loadCards() {
        viewModelScope.launch {
            _state.value = AppListState.Loading
            try {
                val appCardList = withContext(Dispatchers.IO) {
                    appRepository.getAppsList()
                }
                _state.value = AppListState.Content(appCardList)
            } catch(e: Exception) {
                _state.value = AppListState.Error
            }
        }
    }

    fun showRuStoreInfo() {
        viewModelScope.launch {
            _event.send(
                AppListEvent.ShowSnackbar("RuStore information")
            )
        }
    }
}