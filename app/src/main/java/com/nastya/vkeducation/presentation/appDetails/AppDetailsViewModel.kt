package com.nastya.vkeducation.presentation.appDetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastya.vkeducation.domain.GetAppDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val getAppDetailsUseCase: GetAppDetailsUseCase,
) : ViewModel() {
    private val _state: MutableStateFlow<AppDetailsState> =
        MutableStateFlow(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    fun loadAppDetails(id: String) {
        Log.d("ID", id)
        viewModelScope.launch {
            _state.value = AppDetailsState.Loading
            try {
                val appCardList = withContext(Dispatchers.IO) {
                    getAppDetailsUseCase(id)
                }
                _state.value = AppDetailsState.Content(appCardList)
            } catch(e: Exception) {
                Log.d("Details", e.toString())
                _state.value = AppDetailsState.Error
            }
        }
    }
}