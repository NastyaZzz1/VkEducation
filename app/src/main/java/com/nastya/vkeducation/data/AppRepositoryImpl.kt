package com.nastya.vkeducation.data

import com.nastya.vkeducation.data.local.AppDetailsDao
import com.nastya.vkeducation.data.local.AppDetailsEntityMapper
import com.nastya.vkeducation.domain.AppDetails
import com.nastya.vkeducation.domain.AppRepository
import com.nastya.vkeducation.domain.CardApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val api: AppApi,
    private val mapper: AppMapper,
    private val dao: AppDetailsDao,
    private val entityMapper: AppDetailsEntityMapper,
    ): AppRepository {

    override suspend fun getAppDetails(id: String): Flow<AppDetails> {
        return dao.getAppDetails(id).map { entity ->
            if (entity != null) {
                entityMapper.toDomain(entity)
            } else {
                val dto = api.getAppDetails(id)
                val domain = mapper.toDomainAppDetails(dto)
                val entity = entityMapper.toEntity(domain)
                withContext(Dispatchers.IO) {
                    dao.insertAppDetails(entity)
                }
                domain
            }
        }
    }

    override suspend fun getAppsList(): List<CardApp> {
        val dtoList = api.getAppsList()
        val domain = dtoList.map { dto ->
            mapper.toDomainCardApp(dto)
        }
        return domain
    }
}