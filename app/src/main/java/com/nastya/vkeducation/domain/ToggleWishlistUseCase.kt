package com.nastya.vkeducation.domain

import javax.inject.Inject

class ToggleWishlistUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    suspend operator fun invoke(id: String) {
        return appRepository.toggleWishlist(id)
    }
}