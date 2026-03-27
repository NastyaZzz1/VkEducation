package com.nastya.vkeducation.di

import com.nastya.vkeducation.data.AppRepositoryImpl
import com.nastya.vkeducation.domain.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindAppRepository(impl: AppRepositoryImpl): AppRepository
}