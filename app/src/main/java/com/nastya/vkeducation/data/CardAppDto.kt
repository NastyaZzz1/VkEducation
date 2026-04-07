package com.nastya.vkeducation.data

import kotlinx.serialization.Serializable

@Serializable
data class CardAppDto (
    val id: String,
    val iconUrl: String,
    val name: String,
    val description: String,
    val category: String
)