package com.android.leivacourse.artapp

import android.annotation.SuppressLint
import com.android.leivacourse.artapp.api.models.SearchResults
import com.android.leivacourse.artapp.api.service.UnsplashWs
import com.android.leivacourse.artapp.utils.NetworkConnectionInterceptor
import com.android.leivacourse.artapp.utils.Output
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface GalleryArtRepository  {

    suspend fun getArtPhotos(page: Int, queryPage: Int,orderBy: String) : Output<SearchResults>


    companion object : UnsplashWs {
        override suspend fun getArtPhotos(
            query: String,
            page: Int,
            per_pge: Int,
            orientation: String
        ): Response<SearchResults> {
           return  com.android.leivacourse.artapp.api.Retrofit.getUnsplashService().getArtPhotos(query, page, per_pge, orientation)

        }

    }


}