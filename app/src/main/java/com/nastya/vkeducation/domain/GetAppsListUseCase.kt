package com.nastya.vkeducation.domain

import javax.inject.Inject

class GetAppsListUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    suspend operator fun invoke(): List<CardApp> {
        return appRepository.getAppsList()
    }
}