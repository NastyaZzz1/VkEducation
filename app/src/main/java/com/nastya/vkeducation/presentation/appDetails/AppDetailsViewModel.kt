package com.nastya.vkeducation.presentation.appDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastya.vkeducation.domain.GetAppDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val getAppDetailsUseCase: GetAppDetailsUseCase,
) : ViewModel() {
    private val _state: MutableStateFlow<AppDetailsState> =
        MutableStateFlow(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    fun loadAppDetails(id: String) {
        viewModelScope.launch {
            _state.value = AppDetailsState.Loading
            runCatching {
                val appCardList = getAppDetailsUseCase(id)
                _state.value = AppDetailsState.Content(appCardList)
            }.onFailure {
                _state.value = AppDetailsState.Error
            }
        }
    }
}