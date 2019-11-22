package com.android.leivacourse.artapp

import com.android.leivacourse.artapp.api.models.SearchResults
import com.android.leivacourse.artapp.api.service.UnsplashWs
import com.android.leivacourse.artapp.api.models.NoInternetException
import com.android.leivacourse.artapp.utils.Output
import com.google.gson.JsonParseException

class GalleryArtRepositoryImpl(private val unsplashWs: UnsplashWs) : GalleryArtRepository{

    override suspend fun getArtPhotos(page: Int, queryPage: Int,orderBy: String): Output<SearchResults> {
        return try {
            val response = unsplashWs.getArtPhotos("arte",page,queryPage,"landscape")
            return Output.Success(response.body()!!)
        } catch (ex: NoInternetException) {
            ex.printStackTrace()
            Output.Error(ex)
        } catch (ex: JsonParseException) {
            ex.printStackTrace()
            Output.Error(ex)
        }
    }


}