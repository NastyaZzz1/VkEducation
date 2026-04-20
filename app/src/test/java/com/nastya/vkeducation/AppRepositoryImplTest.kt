package com.nastya.vkeducation

import com.nastya.vkeducation.data.AppApi
import com.nastya.vkeducation.data.AppDetailsDto
import com.nastya.vkeducation.data.AppMapper
import com.nastya.vkeducation.data.AppRepositoryImpl
import com.nastya.vkeducation.data.CardAppDto
import com.nastya.vkeducation.data.local.AppDetailsDao
import com.nastya.vkeducation.data.local.AppDetailsEntity
import com.nastya.vkeducation.data.local.AppDetailsEntityMapper
import com.nastya.vkeducation.domain.AppDetails
import com.nastya.vkeducation.domain.CardApp
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
class AppRepositoryImplTest {

    private lateinit var api: AppApi
    private lateinit var mapper: AppMapper
    private lateinit var dao: AppDetailsDao
    private lateinit var entityMapper: AppDetailsEntityMapper
    private lateinit var repository: AppRepositoryImpl

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        api = mock()
        mapper = mock()
        dao = mock()
        entityMapper = mock()

        repository = AppRepositoryImpl(api, mapper, dao, entityMapper)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getAppDetails should return from database when entity exists`() = runTest {
        val id = "123"
        val entity = mock<AppDetailsEntity>()
        val domain = mock<AppDetails>()

        whenever(dao.getAppDetails(id)).thenReturn(flowOf(entity))
        whenever(entityMapper.toDomain(entity)).thenReturn(domain)

        val result = repository.getAppDetails(id).first()

        assertEquals(domain, result)
        verify(api, never()).getAppDetails(any())
        verify(dao, never()).insertAppDetails(any())
    }

    @Test
    fun `getAppDetails should fetch from API when entity not in database`() = runTest {
        val id = "456"
        val dto = mock<AppDetailsDto>()
        val domain = mock<AppDetails>()
        val entity = mock<AppDetailsEntity>()

        whenever(dao.getAppDetails(id)).thenReturn(flowOf(null))
        whenever(api.getAppDetails(id)).thenReturn(dto)
        whenever(mapper.toDomainAppDetails(dto)).thenReturn(domain)
        whenever(entityMapper.toEntity(domain)).thenReturn(entity)

        val result = repository.getAppDetails(id).first()

        assertEquals(domain, result)
        verify(api).getAppDetails(id)
        verify(dao).insertAppDetails(entity)
    }

    @Test
    fun `getAppsList should return list of CardApp from API`() = runTest {
        val dtoList = listOf(mock<CardAppDto>(), mock<CardAppDto>())
        val domainList = listOf(mock<CardApp>(), mock<CardApp>())

        whenever(api.getAppsList()).thenReturn(dtoList)
        whenever(mapper.toDomainCardApp(dtoList[0])).thenReturn(domainList[0])
        whenever(mapper.toDomainCardApp(dtoList[1])).thenReturn(domainList[1])

        val result = repository.getAppsList()

        assertEquals(domainList, result)
        verify(api).getAppsList()
        verify(mapper, times(2)).toDomainCardApp(any())
    }

    @Test
    fun `observeAppDetails should return filtered not null flow from dao`() = runTest {
        val id = "789"
        val entity = mock<AppDetailsEntity>()
        val domain = mock<AppDetails>()

        whenever(dao.getAppDetails(id)).thenReturn(flowOf(entity))
        whenever(entityMapper.toDomain(entity)).thenReturn(domain)

        val result = repository.observeAppDetails(id).first()

        assertEquals(domain, result)
        verify(dao).getAppDetails(id)
    }

    @Test
    fun `toggleWishlist should update wishlist status`() = runTest {
        val id = "123"
        val currentEntity = mock<AppDetailsEntity>()

        whenever(currentEntity.isInWishlist).thenReturn(false)
        whenever(dao.getAppDetails(id)).thenReturn(flowOf(currentEntity))

        repository.toggleWishlist(id)

        verify(dao).updateWishlistStatus(id, true)
    }

    @Test
    fun `toggleWishlist should not update when entity not found`() = runTest {
        val id = "999"
        whenever(dao.getAppDetails(id)).thenReturn(flowOf(null))

        repository.toggleWishlist(id)

        verify(dao, never()).updateWishlistStatus(any(), any())
    }
}