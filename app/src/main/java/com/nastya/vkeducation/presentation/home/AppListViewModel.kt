package com.nastya.vkeducation.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastya.vkeducation.data.AppApi
import com.nastya.vkeducation.data.AppMapper
import com.nastya.vkeducation.data.AppRepositoryImpl
import com.nastya.vkeducation.data.CategoryMapper
import com.nastya.vkeducation.domain.AppRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AppListViewModel: ViewModel() {
    private val _state: MutableStateFlow<AppListState> = MutableStateFlow(AppListState.Loading)
    val state = _state.asStateFlow()

    private val _event = Channel<AppListEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    private val appRepository: AppRepository = AppRepositoryImpl(
        mapper = AppMapper(categoryMapper = CategoryMapper()),
        api = AppApi()
    )

    init {
        loadCards()
    }

    private fun loadCards() {
        viewModelScope.launch {
            val result = runCatching {
                _state.value = AppListState.Loading
                delay(2000)

                val appCardList = appRepository.getAppsList()

                _state.value = AppListState.Content(appCardList)
            }
            if(result.isFailure) {
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