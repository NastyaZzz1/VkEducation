package com.nastya.vkeducation

import com.nastya.vkeducation.data.AppDetailsDto
import com.nastya.vkeducation.data.AppMapper
import com.nastya.vkeducation.data.CardAppDto
import com.nastya.vkeducation.data.CategoryMapper
import com.nastya.vkeducation.domain.Category
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*
import org.junit.Assert.assertEquals

class AppMapperTest {
    private lateinit var categoryMapper: CategoryMapper
    private lateinit var appMapper: AppMapper

    @Before
    fun setUp() {
        categoryMapper = mock()
        appMapper = AppMapper(categoryMapper)
    }

    @Test
    fun `toDomainAppDetails should map DTO to domain correctly`() = runTest {
        val dto = AppDetailsDto(
            id = "123",
            name = "Test App",
            developer = "Test Developer",
            category = "Игры",
            ageRating = 12,
            size = 45.5,
            iconUrl = "http://icon.url",
            screenshotUrlList = listOf("screen1", "screen2"),
            description = "Test Description",
            isInWishlist = true
        )

        val expectedCategory = Category.GAME
        whenever(categoryMapper.toDomain("Игры")).thenReturn(expectedCategory)

        val result = appMapper.toDomainAppDetails(dto)

        assertEquals(dto.id, result.id)
        assertEquals(dto.name, result.name)
        assertEquals(dto.developer, result.developer)
        assertEquals(expectedCategory, result.category)
        assertEquals(dto.ageRating, result.ageRating)
        assertEquals(dto.size.toFloat(), result.size)
        assertEquals(dto.iconUrl, result.iconUrl)
        assertEquals(dto.screenshotUrlList, result.screenshotUrlList)
        assertEquals(dto.description, result.description)
        assertEquals(dto.isInWishlist, result.isInWishlist)
        verify(categoryMapper).toDomain("Игры")
    }

    @Test
    fun `toDomainAppDetails should handle empty screenshot list`() = runTest {
        val dto = AppDetailsDto(
            id = "123",
            name = "Test App",
            developer = "Test Developer",
            category = "Игры",
            ageRating = 12,
            size = 45.5,
            iconUrl = "http://icon.url",
            screenshotUrlList = emptyList(),
            description = "Test Description",
            isInWishlist = false
        )

        whenever(categoryMapper.toDomain(any())).thenReturn(Category.GAME)

        val result = appMapper.toDomainAppDetails(dto)

        assertEquals(emptyList<String>(), result.screenshotUrlList)
    }

    @Test
    fun `toDomainCardApp should map CardAppDto to CardApp correctly`() = runTest {
        val dto = CardAppDto(
            id = "456",
            iconUrl = "http://card.icon",
            name = "Card App",
            description = "Card Description",
            category = "Производительность"
        )

        val expectedCategory = Category.PRODUCTIVITY
        whenever(categoryMapper.toDomain("Производительность")).thenReturn(expectedCategory)

        val result = appMapper.toDomainCardApp(dto)

        assertEquals(dto.id, result.id)
        assertEquals(dto.iconUrl, result.iconUrl)
        assertEquals(dto.name, result.name)
        assertEquals(dto.description, result.description)
        assertEquals(expectedCategory, result.category)
    }

    @Test
    fun `toDomainCardApp should handle all category types`() = runTest {
        val categories = listOf(
            "Производительность" to Category.PRODUCTIVITY,
            "Здоровье и фитнес" to Category.HEALTH_FITNESS,
            "Фото и видео" to Category.PHOTO_VIDEO,
            "Еда и напитки" to Category.FOOD_DRINKS,
            "Образование" to Category.EDUCATION
        )

        categories.forEach { (categoryString, expectedCategory) ->
            val dto = CardAppDto(
                id = "123",
                iconUrl = "url",
                name = "name",
                description = "desc",
                category = categoryString
            )
            whenever(categoryMapper.toDomain(categoryString)).thenReturn(expectedCategory)

            val result = appMapper.toDomainCardApp(dto)

            assertEquals(expectedCategory, result.category)
        }
    }
}