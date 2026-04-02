package com.nastya.vkeducation.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAppDetailsUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    suspend operator fun invoke (id: String): AppDetails {
        return withContext(Dispatchers.IO) {
            appRepository.getAppDetails(id)
        }
    }
}