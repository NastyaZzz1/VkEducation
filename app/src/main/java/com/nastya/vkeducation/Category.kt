package com.nastya.vkeducation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

enum class Category {
    APP, GAME, COMMUNICATION
}

@Composable
fun getCategoryText(category: Category): String = when (category) {
    Category.APP -> stringResource(R.string.category_app)
    Category.GAME -> stringResource(R.string.category_game)
    Category.COMMUNICATION -> stringResource(R.string.category_communication)
}