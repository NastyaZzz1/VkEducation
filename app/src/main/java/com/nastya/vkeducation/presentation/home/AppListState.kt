package com.nastya.vkeducation.presentation.home

import com.nastya.vkeducation.domain.CardApp

sealed interface AppListState {
    data object Loading: AppListState
    data object Error: AppListState
    data class Content(
        val appCards: List<CardApp>
    ): AppListState
}