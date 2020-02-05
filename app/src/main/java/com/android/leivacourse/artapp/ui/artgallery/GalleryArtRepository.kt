package com.android.leivacourse.artapp.ui.artgallery

import com.android.leivacourse.artapp.api.models.SearchResults
import retrofit2.Response

interface GalleryArtRepository {
    suspend fun getArtPhotos(
        query: String,
        page: Int,
        queryPage: Int,
        orderBy: String,
        orientation: String
    ): ResultWrapper<Response<SearchResults>>
}