package com.nastya.vkeducation.data

import com.nastya.vkeducation.domain.AppDetails
import com.nastya.vkeducation.domain.CardApp
import javax.inject.Inject

class AppMapper @Inject constructor(
    private val categoryMapper: CategoryMapper
) {
    fun toDomainAppDetails(dto: AppDto): AppDetails = AppDetails(
        id = dto.id,
        name = dto.name,
        developer = dto.developer,
        category = categoryMapper.toDomain(dto.category),
        ageRating = dto.ageRating,
        size = dto.size.toFloat(),
        iconUrl = dto.iconUrl,
        screenshotUrlList = dto.screenshotUrlList,
        description = dto.description,
    )

    fun toDomainCardApp(dto: AppDto): CardApp = CardApp(
        id = dto.id,
        iconUrl = dto.iconUrl,
        title = dto.title,
        subtitle = dto.subtitle,
        category = categoryMapper.toDomain(dto.category),
    )
}