package com.nastya.vkeducation.data

import retrofit2.http.GET
import retrofit2.http.Path

interface AppApi {
    @GET("/catalog")
    suspend fun getAppsList(): List<CardAppDto>

    @GET("/catalog/{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsDto
}