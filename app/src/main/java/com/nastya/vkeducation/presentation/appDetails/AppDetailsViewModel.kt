package com.nastya.vkeducation.presentation.appDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastya.vkeducation.data.AppApi
import com.nastya.vkeducation.data.AppMapper
import com.nastya.vkeducation.data.AppRepositoryImpl
import com.nastya.vkeducation.data.CategoryMapper
import com.nastya.vkeducation.domain.AppRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppDetailsViewModel: ViewModel() {
    private val _state: MutableStateFlow<AppDetailsState> =
        MutableStateFlow(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    private val appRepository: AppRepository = AppRepositoryImpl(
        mapper = AppMapper(categoryMapper = CategoryMapper()),
        api = AppApi()
    )

    init {
        loadApp()
    }

    fun loadApp() {
        viewModelScope.launch {
            val result = runCatching {
                _state.value = AppDetailsState.Loading
                delay(2000)

                val appDetails = appRepository.getAppDetails("1")

                _state.value = AppDetailsState.Content(appDetails)
            }
            if (result.isFailure) {
                _state.value = AppDetailsState.Error
            }
        }
    }
}