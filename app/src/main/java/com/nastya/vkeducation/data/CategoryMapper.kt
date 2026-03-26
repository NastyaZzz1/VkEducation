package com.nastya.vkeducation.data

import com.nastya.vkeducation.domain.Category
import javax.inject.Inject

class CategoryMapper @Inject constructor() {
    fun toDomain(category: String): Category {
        return when (category) {
            "Производительность" -> Category.PRODUCTIVITY
            "Здоровье и фитнес" -> Category.HEALTH_FITNESS
            "Фото и видео" -> Category.PHOTO_VIDEO
            "Еда и напитки" -> Category.FOOD_DRINKS
            "Образование" -> Category.EDUCATION
            "Образ жизни" -> Category.LIFESTYLE
            "Шопинг" -> Category.SHOPPING
            "Новости" -> Category.NEWS
            "Музыка" -> Category.MUSIC
            "Игры" -> Category.GAME
            "Утилиты" -> Category.UTILITIES
            "Бизнес" -> Category.BUSINESS
            "Книги и справочники" -> Category.BOOKS_GUIDES
            "Навигация" -> Category.NAVIGATION
            "Общение" -> Category.SOCIAL
            else -> Category.PRODUCTIVITY
        }
    }
}