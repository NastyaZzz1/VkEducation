package com.nastya.vkeducation.domain

interface AppRepository {
    suspend fun getAppsList(): List<CardApp>
    suspend fun getAppDetails(id: String): AppDetails
}