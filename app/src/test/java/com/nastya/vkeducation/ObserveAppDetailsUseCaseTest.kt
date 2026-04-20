package com.nastya.vkeducation

import com.nastya.vkeducation.domain.AppDetails
import com.nastya.vkeducation.domain.AppRepository
import com.nastya.vkeducation.domain.ObserveAppDetailsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.junit.Assert.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ObserveAppDetailsUseCaseTest {

    private lateinit var appRepository: AppRepository
    private lateinit var useCase: ObserveAppDetailsUseCase

    @Before
    fun setUp() {
        appRepository = mock()
        useCase = ObserveAppDetailsUseCase(appRepository)
    }

    @Test
    fun `invoke should return observable flow from repository`() = runTest {
        val id = "123"
        val expectedFlow = flowOf(mock<AppDetails>())
        whenever(appRepository.observeAppDetails(id)).thenReturn(expectedFlow)

        val result = useCase(id)

        assertEquals(expectedFlow, result)
        verify(appRepository).observeAppDetails(id)
    }

    @Test
    fun `invoke should handle multiple observations`() {
        val id = "456"
        val expectedFlow = flowOf(mock<AppDetails>())
        whenever(appRepository.observeAppDetails(id)).thenReturn(expectedFlow)

        val result1 = useCase(id)
        val result2 = useCase(id)

        assertEquals(result1, result2)
        verify(appRepository, times(2)).observeAppDetails(id)
    }
}