package com.nastya.vkeducation.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAppDetailsUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    operator fun invoke(id: String): Flow<AppDetails> {
        return appRepository.observeAppDetails(id)
    }
}