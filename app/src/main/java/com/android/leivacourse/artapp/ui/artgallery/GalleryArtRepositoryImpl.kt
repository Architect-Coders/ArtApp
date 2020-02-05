package com.android.leivacourse.artapp.ui.artgallery

import com.android.leivacourse.artapp.api.models.SearchResults
import com.android.leivacourse.artapp.api.service.UnsplashWs
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


class GalleryArtRepositoryImpl(private val unsplashWs: UnsplashWs) :
    GalleryArtRepository {

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
    }

}