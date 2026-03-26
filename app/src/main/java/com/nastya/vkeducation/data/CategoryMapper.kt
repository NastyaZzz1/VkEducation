package com.nastya.vkeducation.data

import com.nastya.vkeducation.domain.Category

class CategoryMapper {
    fun toDomain(category: String): Category {
        return when(category) {
            "App" -> Category.APP
            "Game" -> Category.GAME
            "Communication" -> Category.COMMUNICATION
            else -> throw IllegalStateException("Category is not found")
        }
    }
}