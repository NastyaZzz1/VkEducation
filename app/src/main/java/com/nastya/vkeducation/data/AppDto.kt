package com.nastya.vkeducation.data

data class AppDto (
    val id: String,
    val name: String,
    val developer: String,
    val category: String,
    val ageRating: Int,
    val size: Double,
    val iconUrl: String,
    val screenshotUrlList: List<String>,
    val description: String,
    val title: String,
    val subtitle: String,
)