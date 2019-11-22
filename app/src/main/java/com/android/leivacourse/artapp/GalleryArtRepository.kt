package com.android.leivacourse.artapp

import com.android.leivacourse.artapp.api.models.SearchResults
import com.android.leivacourse.artapp.utils.Output

interface GalleryArtRepository {

    suspend fun getArtPhotos(page: Int, queryPage: Int,orderBy: String) : Output<SearchResults>

}