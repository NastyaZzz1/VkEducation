package com.nastya.vkeducation

import com.nastya.vkeducation.domain.AppRepository
import com.nastya.vkeducation.domain.ToggleWishlistUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class)
class ToggleWishlistUseCaseTest {

    private lateinit var appRepository: AppRepository
    private lateinit var useCase: ToggleWishlistUseCase
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        appRepository = mock()
        useCase = ToggleWishlistUseCase(appRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `invoke should call repository toggleWishlist`() = runTest {
        val id = "123"

        useCase(id)

        verify(appRepository).toggleWishlist(id)
    }

    @Test
    fun `invoke should handle empty string id`() = runTest {
        val id = ""

        useCase(id)

        verify(appRepository).toggleWishlist(id)
    }

    @Test
    fun `invoke should handle multiple ids`() = runTest {
        val ids = listOf("1", "2", "3")

        ids.forEach { id ->
            useCase(id)
        }

        ids.forEach { id ->
            verify(appRepository).toggleWishlist(id)
        }
        verify(appRepository, times(3)).toggleWishlist(any())
    }
}