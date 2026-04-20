package com.nastya.vkeducation

import com.nastya.vkeducation.data.local.AppDetailsEntity
import com.nastya.vkeducation.data.local.AppDetailsEntityMapper
import com.nastya.vkeducation.domain.AppDetails
import com.nastya.vkeducation.domain.Category
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull

class AppDetailsEntityMapperTest {
    private lateinit var entityMapper: AppDetailsEntityMapper

    @Before
    fun setUp() {
        entityMapper = AppDetailsEntityMapper()
    }

    @Test
    fun `toEntity should map domain to entity correctly`() {
        val domain = AppDetails(
            id = "123",
            name = "Test App",
            developer = "Developer",
            category = Category.GAME,
            ageRating = 18,
            size = 50.5f,
            iconUrl = "http://icon.url",
            screenshotUrlList = listOf("screen1", "screen2"),
            description = "Description",
            isInWishlist = true
        )

        val result = entityMapper.toEntity(domain)

        assertEquals(domain.id, result.id)
        assertEquals(domain.name, result.name)
        assertEquals(domain.developer, result.developer)
        assertEquals(domain.category, result.category)
        assertEquals(domain.ageRating, result.ageRating)
        assertEquals(domain.size, result.size)
        assertEquals(domain.iconUrl, result.iconUrl)
        assertNull(result.screenshots)
        assertEquals(domain.description, result.description)
    }

    @Test
    fun `toEntity should set screenshots to null`() {
        val domain = AppDetails(
            id = "123",
            name = "Test App",
            developer = "Developer",
            category = Category.UTILITIES,
            ageRating = 12,
            size = 30.0f,
            iconUrl = "url",
            screenshotUrlList = listOf("screen1", "screen2", "screen3"),
            description = "desc",
            isInWishlist = false
        )

        val result = entityMapper.toEntity(domain)

        assertNull(result.screenshots)
    }

    @Test
    fun `toDomain should map entity to domain correctly`() {
        val entity = AppDetailsEntity(
            id = "456",
            name = "Entity App",
            developer = "Entity Dev",
            category = Category.GAME,
            ageRating = 16,
            size = 75.8f,
            iconUrl = "http://entity.icon",
            screenshots = null,
            description = "Entity Description",
            isInWishlist = true
        )

        val result = entityMapper.toDomain(entity)

        assertEquals(entity.id, result.id)
        assertEquals(entity.name, result.name)
        assertEquals(entity.developer, result.developer)
        assertEquals(entity.category, result.category)
        assertEquals(entity.ageRating, result.ageRating)
        assertEquals(entity.size, result.size)
        assertEquals(entity.iconUrl, result.iconUrl)
        assertNull(result.screenshotUrlList)
        assertEquals(entity.description, result.description)
        assertEquals(entity.isInWishlist, result.isInWishlist)
    }

    @Test
    fun `toDomain should handle entity with false wishlist status`() {
        val entity = AppDetailsEntity(
            id = "789",
            name = "Test",
            developer = "Dev",
            category = Category.UTILITIES,
            ageRating = 0,
            size = 10.0f,
            iconUrl = "url",
            screenshots = null,
            description = "desc",
            isInWishlist = false
        )

        val result = entityMapper.toDomain(entity)

        assertEquals(false, result.isInWishlist)
    }

    @Test
    fun `toEntity and toDomain should be inverse operations`() {
        val originalDomain = AppDetails(
            id = "999",
            name = "Round Trip App",
            developer = "Round Dev",
            category = Category.EDUCATION,
            ageRating = 10,
            size = 20.5f,
            iconUrl = "round.url",
            screenshotUrlList = listOf("s1", "s2"),
            description = "Round Description",
            isInWishlist = true
        )

        val entity = entityMapper.toEntity(originalDomain)
        val resultDomain = entityMapper.toDomain(entity)

        assertEquals(originalDomain.id, resultDomain.id)
        assertEquals(originalDomain.name, resultDomain.name)
        assertEquals(originalDomain.developer, resultDomain.developer)
        assertEquals(originalDomain.category, resultDomain.category)
        assertEquals(originalDomain.ageRating, resultDomain.ageRating)
        assertEquals(originalDomain.size, resultDomain.size)
        assertEquals(originalDomain.iconUrl, resultDomain.iconUrl)
        assertEquals(originalDomain.description, resultDomain.description)
    }
}