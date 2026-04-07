package com.nastya.vkeducation.data

import kotlinx.serialization.Serializable

@Serializable
data class AppDetailsDto (
    val id: String,
    val name: String,
    val category: String,
    val iconUrl: String,
    val description: String,
    val developer: String,
    val ageRating: Int,
    val size: Double,
    val screenshotUrlList: List<String> = emptyList(),
)