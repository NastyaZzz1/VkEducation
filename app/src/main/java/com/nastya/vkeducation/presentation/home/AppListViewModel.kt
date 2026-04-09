package com.nastya.vkeducation.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastya.vkeducation.domain.GetAppsListUseCase
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
    private val getAppsListUseCase: GetAppsListUseCase,
    ) : ViewModel() {
    private val _state: MutableStateFlow<AppListState> = MutableStateFlow(AppListState.Loading)
    val state = _state.asStateFlow()

    private val _event = Channel<AppListEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    init {
        viewModelScope.launch {
            loadCards()
        }
    }

    private suspend fun loadCards() {
        _state.value = AppListState.Loading
        runCatching {
            val appCardList = withContext(Dispatchers.IO) {
                getAppsListUseCase()
            }
            _state.value = AppListState.Content(appCardList)
        }.onFailure {
            _state.value = AppListState.Error
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