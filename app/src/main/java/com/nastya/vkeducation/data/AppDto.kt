package com.nastya.vkeducation.data

import kotlinx.serialization.Serializable

@Serializable
data class AppDto (
    val id: String,
    val name: String,
    val category: String,
    val iconUrl: String,
    val description: String,
    val developer: String = "",
    val ageRating: Int = 5,
    val size: Double = 5.0,
    val screenshotUrlList: List<String> = emptyList(),
)