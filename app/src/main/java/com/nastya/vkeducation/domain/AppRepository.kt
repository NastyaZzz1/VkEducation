package com.nastya.vkeducation.domain

import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun getAppsList(): List<CardApp>
    suspend fun getAppDetails(id: String): Flow<AppDetails>
}