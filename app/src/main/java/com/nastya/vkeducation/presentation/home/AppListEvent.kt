package com.nastya.vkeducation.presentation.home

sealed interface AppListEvent {
    data class ShowSnackbar(val message: String) : AppListEvent
}