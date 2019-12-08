package com.android.leivacourse.artapp.api

import com.android.leivacourse.artapp.BuildConfig
import com.android.leivacourse.artapp.utils.AuthInterceptor
import com.android.leivacourse.artapp.utils.NetworkConnectionInterceptor
import com.android.leivacourse.artapp.api.service.UnsplashWs
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    private fun getInstance(networkConnectionInterceptor: NetworkConnectionInterceptor): Retrofit {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(AuthInterceptor(BuildConfig.KEY))
            .addInterceptor(networkConnectionInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getUnsplashService(networkConnectionInterceptor: NetworkConnectionInterceptor): UnsplashWs {
        return getInstance(networkConnectionInterceptor).create(UnsplashWs::class.java)
    }

}