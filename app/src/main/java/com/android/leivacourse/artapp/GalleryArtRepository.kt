package com.android.leivacourse.artapp

import com.android.leivacourse.artapp.api.models.SearchResults
import com.android.leivacourse.artapp.api.service.UnsplashWs
import com.android.leivacourse.artapp.utils.Output
import retrofit2.Response

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