package com.kyawlinnthant.data

import com.kyawlinnthant.data.repository.AuthApiRepository
import com.kyawlinnthant.data.repository.AuthApiRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AuthRepositoryModule {
    @Singleton
    @Binds
    fun bindAuthApi(api : AuthApiRepositoryImpl) : AuthApiRepository
}