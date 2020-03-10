package com.android.leivacourse.artapp.data

import com.android.leivacourse.artapp.data.local.model.ArtDetail

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveArt(art: ArtDetail)
    suspend fun getFavoritesArt(): List<ArtDetail>
    suspend fun removeArt(art: ArtDetail)
}