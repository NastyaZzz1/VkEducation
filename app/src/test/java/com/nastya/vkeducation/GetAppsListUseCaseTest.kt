package com.nastya.vkeducation

import com.nastya.vkeducation.domain.AppRepository
import com.nastya.vkeducation.domain.CardApp
import com.nastya.vkeducation.domain.GetAppsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GetAppsListUseCaseTest {

    private lateinit var appRepository: AppRepository
    private lateinit var useCase: GetAppsListUseCase
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        appRepository = mock()
        useCase = GetAppsListUseCase(appRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `invoke should return list from repository`() = runTest {
        val expectedList = listOf(mock<CardApp>(), mock<CardApp>())
        whenever(appRepository.getAppsList()).thenReturn(expectedList)

        val result = useCase()

        assertEquals(expectedList, result)
        verify(appRepository).getAppsList()
    }

    @Test
    fun `invoke should return empty list when repository returns empty`() = runTest {
        val expectedList = emptyList<CardApp>()
        whenever(appRepository.getAppsList()).thenReturn(expectedList)

        val result = useCase()

        assertEquals(0, result.size)
        assertEquals(expectedList, result)
    }
}