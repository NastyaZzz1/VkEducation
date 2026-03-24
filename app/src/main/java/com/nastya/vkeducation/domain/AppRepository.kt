package com.nastya.vkeducation.domain

interface AppRepository {
    suspend fun getAppDetails(id: String): AppDetails
    suspend fun getAppsList(): List<CardApp>
}