package com.android.leivacourse.artapp.utils

import com.android.leivacourse.artapp.data.ArtRepository
import com.android.leivacourse.artapp.data.local.model.ArtDetail

class ToggleArtFavorite(private val artRepository: ArtRepository) {
    suspend fun invoke(art: ArtDetail): ArtDetail = with(art) {
        val arrArt: List<ArtDetail> = artRepository.getFavoriteArt()
        lateinit var artInDb: ArtDetail
        var isFavorite = false
        for (artDetail in arrArt) {
            if (artDetail.urls?.equals(art.urls)!!) {
                isFavorite = true
                artInDb = artDetail
                break
            }
        }
        if (isFavorite) {
            art.favorite = false
            artRepository.deleteFavorite(artInDb)
        } else {
            art.favorite = true
            artRepository.insertFavorite(art)
        }
       return art
    }

    suspend fun check(art: ArtDetail): ArtDetail? = with(art) {
        val arrArt: List<ArtDetail> = artRepository.getFavoriteArt()
         var artInDb: ArtDetail? = null
        for (artDetail in arrArt) {
            if (artDetail.urls?.equals(art.urls)!!) {
                artInDb = artDetail
                break
            }
        }
       return artInDb
    }
}