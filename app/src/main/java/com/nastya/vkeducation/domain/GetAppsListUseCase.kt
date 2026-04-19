package com.nastya.vkeducation.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAppsListUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    suspend operator fun invoke(): List<CardApp> = withContext(Dispatchers.IO) {
        return@withContext appRepository.getAppsList()
    }
}