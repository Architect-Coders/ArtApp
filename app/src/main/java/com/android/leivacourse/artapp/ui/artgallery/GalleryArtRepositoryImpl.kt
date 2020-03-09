package com.android.leivacourse.artapp.ui.artgallery

import com.android.leivacourse.artapp.ArtApp
import com.android.leivacourse.artapp.api.models.SearchResults
import com.android.leivacourse.artapp.api.service.UnsplashWs
import com.android.leivacourse.artapp.data.local.model.ImageDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


class GalleryArtRepositoryImpl(private val unsplashWs: UnsplashWs, private val application : ArtApp) :
    GalleryArtRepository {


    private val db = application.db


    override suspend fun getArtPhotos(query: String,page: Int, queryPage: Int,orderBy: String, orientation: String): ResultWrapper<Response<SearchResults>> {
        return try {
            ResultWrapper.Success(unsplashWs.getArtPhotos(query, page, queryPage, orientation))
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    ResultWrapper.GenericError(code, errorResponse)
                }
                else -> {
                    ResultWrapper.GenericError(null, null)
                }
            }
        }
    }

    private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
        return try {
            throwable.response()?.errorBody()?.source()?.let {
              ErrorResponse(listOf(it.toString()))
            }
        } catch (exception: Exception) {
            null
        }
    }


    suspend fun update(imageDetail: ImageDetail) = withContext(Dispatchers.IO) {
        db.imageDetailDao().updateImage(imageDetail)
    }

    companion object {

        private var INSTANCE: GalleryArtRepositoryImpl? = null
        fun getInstance(unsplashWs: UnsplashWs): GalleryArtRepository {
            return INSTANCE
                ?: GalleryArtRepositoryImpl(
                    unsplashWs
                )
                .apply { INSTANCE = this }
        }

        fun destroyInstance() {
            INSTANCE = null
        }

        fun updateFavorite(updatedArt: ImageDetail) {

        }

    }


}