package com.kyawlinnthant.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kyawlinnthant.network.interceptor.HeaderInterceptor
import com.kyawlinnthant.network.interceptor.TokenAuthenticator
import com.kyawlinnthant.network.service.RefreshTokenService
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json {

        return Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
            coerceInputValues = true
        }
    }

    @Provides
    @Singleton
    fun provideConverterFactory(
        json: Json
    ): Converter.Factory = json.asConverterFactory("application/json".toMediaType())

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class AuthClient

    @Provides
    @Singleton
    @AuthClient
    fun provideAuthClient(): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(OkHttpProfilerInterceptor())
        .build()

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class TokenClient

    @Provides
    @Singleton
    @TokenClient
    fun provideTokenClient(
        header: HeaderInterceptor? = null,
        authenticator: TokenAuthenticator? = null,
    ): OkHttpClient = OkHttpClient.Builder()
        .apply {
            header?.let {
                this.addInterceptor(it)
            }
            authenticator?.let {
                this.authenticator(it)
            }
        }.build()


    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class AuthRetrofit

    @Provides
    @Singleton
    @AuthRetrofit
    fun provideAuthRetrofit(
        @AuthClient client: OkHttpClient,
        factory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(factory)
        .build()

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class TokenRetrofit

    @Provides
    @Singleton
    @TokenRetrofit
    fun provideTokenRetrofit(
        @TokenClient client: OkHttpClient,
        factory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(factory)
        .build()

    @Provides
    @Singleton
    fun provideRefreshTokenApi(
        @AuthRetrofit retrofit: Retrofit
    ): RefreshTokenService = retrofit.create(RefreshTokenService::class.java)
}