package com.nastya.vkeducation

sealed interface AppDetailsState {
    data object Loading: AppDetailsState
    data object Error: AppDetailsState
    data class Content(
        val appDetails: AppDetails
    ): AppDetailsState
}