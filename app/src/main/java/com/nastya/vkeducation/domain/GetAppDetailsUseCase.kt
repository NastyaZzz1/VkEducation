package com.nastya.vkeducation.domain

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAppDetailsUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    suspend operator fun invoke (id: String): Flow<AppDetails> {
        return appRepository.getAppDetails(id)
    }
}