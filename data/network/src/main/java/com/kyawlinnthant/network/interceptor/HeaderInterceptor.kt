package com.kyawlinnthant.network.interceptor

import com.kyawlinnthant.encrypted.EncryptedPrefSource
import com.kyawlinnthant.pref.PrefDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(
    private val ds: PrefDataStore,
    private val pref: EncryptedPrefSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        runBlocking {
            val accessToken = pref.getAccessToken()
            val tokenType = ds.pullTokenType().firstOrNull() ?: ""

            requestBuilder.addHeader(
                "Authorization",
                "$tokenType $accessToken"
            )
        }.also {
            return chain.proceed(requestBuilder.build())
        }
    }
}
