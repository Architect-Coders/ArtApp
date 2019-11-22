package com.android.leivacourse.artapp.utils

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val clientId: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader("Authorization", "Client-ID $clientId")
                .build()
        )
    }
}