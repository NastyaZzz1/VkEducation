package com.nastya.vkeducation

import com.nastya.vkeducation.data.CategoryMapper
import com.nastya.vkeducation.domain.Category
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

class CategoryMapperTest {

    private lateinit var categoryMapper: CategoryMapper

    @Before
    fun setUp() {
        categoryMapper = CategoryMapper()
    }

    @Test
    fun `toDomain should map Производительность to PRODUCTIVITY`() {
        val result = categoryMapper.toDomain("Производительность")
        assertEquals(Category.PRODUCTIVITY, result)
    }

    @Test
    fun `toDomain should map Здоровье и фитнес to HEALTH_FITNESS`() {
        val result = categoryMapper.toDomain("Здоровье и фитнес")
        assertEquals(Category.HEALTH_FITNESS, result)
    }

    @Test
    fun `toDomain should map Фото и видео to PHOTO_VIDEO`() {
        val result = categoryMapper.toDomain("Фото и видео")
        assertEquals(Category.PHOTO_VIDEO, result)
    }

    @Test
    fun `toDomain should map Игры to GAME`() {
        val result = categoryMapper.toDomain("Игры")
        assertEquals(Category.GAME, result)
    }

    @Test
    fun `toDomain should return PRODUCTIVITY for unknown category`() {
        val result = categoryMapper.toDomain("Неизвестная категория")
        assertEquals(Category.PRODUCTIVITY, result)
    }
}