package com.nastya.vkeducation.data.local

import androidx.room.TypeConverter
import com.nastya.vkeducation.domain.Category

class CategoryConverter {
    @TypeConverter
    fun fromCategory(category: Category): String = category.name

    @TypeConverter
    fun toCategory(categoryName: String): Category = Category.valueOf(categoryName)
}