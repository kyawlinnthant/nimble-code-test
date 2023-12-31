package com.kyawlinnthant.data

import com.kyawlinnthant.data.service.AuthService
import com.kyawlinnthant.network.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideAuthApiService(
        @NetworkModule.AuthRetrofit retrofit: Retrofit
    ): AuthService = retrofit.create(AuthService::class.java)
}
