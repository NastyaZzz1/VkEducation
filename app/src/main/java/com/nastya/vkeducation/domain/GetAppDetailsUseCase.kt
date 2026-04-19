package com.nastya.vkeducation.domain

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetAppDetailsUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    suspend operator fun invoke (id: String): Flow<AppDetails> = withContext(Dispatchers.IO) {
        return@withContext appRepository.getAppDetails(id)
    }
}