package com.android.leivacourse.artapp.api

import com.android.leivacourse.artapp.utils.AuthInterceptor
import com.android.leivacourse.artapp.utils.NetworkConnectionInterceptor
import com.android.leivacourse.artapp.api.service.UnsplashWs
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    val KEY = "ccbb1f20d6361b9583465f467321aaab680c3c97b5d7d24b07cf974fb21107cd"
    val BASE_URL = "https://api.unsplash.com/"

   // private fun getInstance(networkConnectionInterceptor: NetworkConnectionInterceptor ): Retrofit {
    private fun getInstance(): Retrofit {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(
                AuthInterceptor(
                    KEY
                )
            )
            //.addInterceptor(networkConnectionInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //fun getUnsplashService(networkConnectionInterceptor: NetworkConnectionInterceptor) : UnsplashWs {
    fun getUnsplashService() : UnsplashWs {
        return getInstance(
//networkConnectionInterceptor
        ).create(UnsplashWs::class.java)
    }

}