package com.nastya.vkeducation.data

import com.nastya.vkeducation.domain.Category
import javax.inject.Inject

class CategoryMapper @Inject constructor() {
    fun toDomain(category: String): Category {
        return when(category) {
            "App" -> Category.APP
            "Game" -> Category.GAME
            "Communication" -> Category.COMMUNICATION
            else -> throw IllegalStateException("Category is not found")
        }
    }
}