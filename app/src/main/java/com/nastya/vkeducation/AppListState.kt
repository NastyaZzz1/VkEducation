package com.nastya.vkeducation

sealed interface AppListState {
    data object Loading: AppListState
    data object Error: AppListState
    data class Content(
        val appCards: List<CardApp>
    ): AppListState
}