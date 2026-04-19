package com.nastya.vkeducation.presentation.appDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastya.vkeducation.domain.GetAppDetailsUseCase
import com.nastya.vkeducation.domain.ObserveAppDetailsUseCase
import com.nastya.vkeducation.domain.ToggleWishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val getAppDetailsUseCase: GetAppDetailsUseCase,
    private val observeAppDetailsUseCase: ObserveAppDetailsUseCase,
    private val toggleWishlistUseCase: ToggleWishlistUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val appId: String = savedStateHandle["appId"] ?: ""

    private val _state: MutableStateFlow<AppDetailsState> =
        MutableStateFlow(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            loadAppDetails()
        }
    }

    private suspend fun loadAppDetails() {
        _state.value = AppDetailsState.Loading

        getAppDetailsUseCase(appId).catch { e ->
            _state.value = AppDetailsState.Error
        }.collect { appDetails ->
            _state.value = AppDetailsState.Content(appDetails)

            observeAppDetails()
        }
    }

    private suspend fun observeAppDetails() {
        observeAppDetailsUseCase(appId).catch {
            _state.value = AppDetailsState.Error
        }.collect {
            _state.value = AppDetailsState.Content(it)
        }
    }

    fun toggleWishlist() {
        viewModelScope.launch {
            toggleWishlistUseCase(appId)
        }
    }
}