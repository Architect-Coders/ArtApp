package com.android.leivacourse.artapp.data

import com.android.leivacourse.artapp.data.local.model.ArtDetail

class ArtRepository(
    private val localDataSource: LocalDataSource
) {

    suspend fun getFavoriteArt(): List<ArtDetail> {
        return localDataSource.getFavoritesArt()
    }

    suspend fun deleteFavorite(art: ArtDetail) = localDataSource.removeArt(art)
    suspend fun insertFavorite(art: ArtDetail) = localDataSource.saveArt(art)

}