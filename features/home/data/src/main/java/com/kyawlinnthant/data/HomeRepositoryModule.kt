package com.kyawlinnthant.data

import com.kyawlinnthant.data.repository.HomeApiRepository
import com.kyawlinnthant.data.repository.HomeApiRepositoryImpl
import com.kyawlinnthant.data.repository.HomeDbRepository
import com.kyawlinnthant.data.repository.HomeDbRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface HomeRepositoryModule {

    @Singleton
    @Binds
    fun bindHomeApi(api: HomeApiRepositoryImpl): HomeApiRepository

    @Singleton
    @Binds
    fun bindHomeDb(db: HomeDbRepositoryImpl): HomeDbRepository
}