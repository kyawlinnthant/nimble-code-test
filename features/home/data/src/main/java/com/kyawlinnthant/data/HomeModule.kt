package com.kyawlinnthant.data

import com.kyawlinnthant.data.service.HomeService
import com.kyawlinnthant.network.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Provides
    @Singleton
    fun provideHomeApiService(
        @NetworkModule.TokenRetrofit retrofit: Retrofit
    ): HomeService = retrofit.create(HomeService::class.java)
}