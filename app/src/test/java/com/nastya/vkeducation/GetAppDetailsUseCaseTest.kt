package com.nastya.vkeducation

import com.nastya.vkeducation.domain.AppDetails
import com.nastya.vkeducation.domain.AppRepository
import com.nastya.vkeducation.domain.GetAppDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*
import org.junit.Assert.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class GetAppDetailsUseCaseTest {

    private lateinit var appRepository: AppRepository
    private lateinit var useCase: GetAppDetailsUseCase
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        appRepository = mock()
        useCase = GetAppDetailsUseCase(appRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `invoke should return flow from repository`() = runTest {
        val id = "123"
        val expectedFlow = flowOf(mock<AppDetails>())
        whenever(appRepository.getAppDetails(id)).thenReturn(expectedFlow)

        val result = useCase(id)

        assertEquals(expectedFlow, result)
        verify(appRepository).getAppDetails(id)
    }

    @Test
    fun `invoke should handle different app ids`() = runTest {
        val ids = listOf("1", "2", "3")
        ids.forEach { id ->
            whenever(appRepository.getAppDetails(id)).thenReturn(flowOf(mock()))
        }

        ids.forEach { id ->
            val result = useCase(id)
            assert(result is Flow<AppDetails>)
            verify(appRepository).getAppDetails(id)
        }
    }
}