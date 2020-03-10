package com.android.leivacourse.artapp.utils

import android.util.Log
import com.android.leivacourse.artapp.data.ArtRepository
import com.android.leivacourse.artapp.data.local.model.ArtDetail

class ToggleArtFavorite(private val artRepository: ArtRepository) {
    suspend fun invoke(art: ArtDetail): ArtDetail = with(art) {
        Log.wtf("YEHO","art: "+this.description+" antes operacion: "+artRepository.getFavoriteArt().size)
        copy(favorite = !favorite).also { ArtDetail ->
            if (favorite) {
                artRepository.deleteFavorite(ArtDetail)
            }else {
                artRepository.insertFavorite(ArtDetail)
            }
            Log.wtf("YEHO" ,"art: "+ArtDetail.description+"despues cuantos favoritos: "+artRepository.getFavoriteArt().size)
        }
    }
}