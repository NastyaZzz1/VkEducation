package com.nastya.vkeducation.presentation.appDetails

import com.nastya.vkeducation.domain.AppDetails

sealed interface AppDetailsState {
    data object Loading: AppDetailsState
    data object Error: AppDetailsState
    data class Content(
        val appDetails: AppDetails
    ): AppDetailsState
}