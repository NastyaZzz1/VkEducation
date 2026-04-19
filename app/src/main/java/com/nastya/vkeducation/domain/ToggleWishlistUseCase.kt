package com.nastya.vkeducation.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ToggleWishlistUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    suspend operator fun invoke(id: String) = withContext(Dispatchers.IO) {
        return@withContext appRepository.toggleWishlist(id)
    }
}