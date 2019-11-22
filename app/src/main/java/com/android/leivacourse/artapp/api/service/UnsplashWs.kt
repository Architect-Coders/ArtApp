package com.android.leivacourse.artapp.api.service

import com.android.leivacourse.artapp.api.models.SearchResults
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashWs {

    @GET("search/photos")
    suspend fun getArtPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_pge") per_pge: Int,
        @Query("orientation") orientation: String
    ): Response<SearchResults>

}