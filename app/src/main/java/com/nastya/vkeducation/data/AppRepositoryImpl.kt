package com.nastya.vkeducation.data

import com.nastya.vkeducation.domain.AppRepository
import com.nastya.vkeducation.domain.AppDetails
import com.nastya.vkeducation.domain.CardApp

class AppRepositoryImpl(
    private val mapper: AppMapper,
    private val api: AppApi
): AppRepository {
    override suspend fun getAppDetails(id: String): AppDetails {
        val dto = api.getAppDetails(id)
        val domain = mapper.toDomainAppDetails(dto)
        return domain
    }

    override suspend fun getAppsList(): List<CardApp> {
        val dtoList = api.getAppsList()
        val domain = dtoList.map { dto ->
            mapper.toDomainCardApp(dto)
        }
        return domain
    }
}