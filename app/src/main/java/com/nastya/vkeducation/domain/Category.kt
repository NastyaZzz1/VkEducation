package com.nastya.vkeducation.domain

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.nastya.vkeducation.R
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Category {
    @SerialName("Производительность")
    PRODUCTIVITY,

    @SerialName("Здоровье и фитнес")
    HEALTH_FITNESS,

    @SerialName("Фото и видео")
    PHOTO_VIDEO,

    @SerialName("Еда и напитки")
    FOOD_DRINKS,

    @SerialName("Образование")
    EDUCATION,

    @SerialName("Образ жизни")
    LIFESTYLE,

    @SerialName("Шопинг")
    SHOPPING,

    @SerialName("Новости")
    NEWS,

    @SerialName("Музыка")
    MUSIC,

    @SerialName("Игры")
    GAME,

    @SerialName("Утилиты")
    UTILITIES,

    @SerialName("Бизнес")
    BUSINESS,

    @SerialName("Книги и справочники")
    BOOKS_GUIDES,

    @SerialName("Навигация")
    NAVIGATION,

    @SerialName("Общение")
    SOCIAL
}

@Composable
fun getCategoryText(category: Category): String = when (category) {
    Category.PRODUCTIVITY -> stringResource(R.string.category_productivity)
    Category.HEALTH_FITNESS -> stringResource(R.string.category_health_fitness)
    Category.PHOTO_VIDEO -> stringResource(R.string.category_photo_video)
    Category.FOOD_DRINKS -> stringResource(R.string.category_food_drinks)
    Category.EDUCATION -> stringResource(R.string.category_education)
    Category.LIFESTYLE -> stringResource(R.string.category_lifestyle)
    Category.SHOPPING -> stringResource(R.string.category_shopping)
    Category.NEWS -> stringResource(R.string.category_news)
    Category.MUSIC -> stringResource(R.string.category_music)
    Category.GAME -> stringResource(R.string.category_game)
    Category.UTILITIES -> stringResource(R.string.category_utilities)
    Category.BUSINESS -> stringResource(R.string.category_business)
    Category.BOOKS_GUIDES -> stringResource(R.string.category_books_guides)
    Category.NAVIGATION -> stringResource(R.string.category_navigation)
    Category.SOCIAL -> stringResource(R.string.category_social)
}