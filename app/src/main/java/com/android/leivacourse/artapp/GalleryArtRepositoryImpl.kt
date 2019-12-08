package com.android.leivacourse.artapp

import com.android.leivacourse.artapp.api.models.NoInternetException
import com.android.leivacourse.artapp.api.models.SearchResults
import com.android.leivacourse.artapp.api.service.UnsplashWs
import com.android.leivacourse.artapp.utils.Output
import com.google.gson.JsonParseException


class GalleryArtRepositoryImpl(private val unsplashWs: UnsplashWs) : GalleryArtRepository{

    override suspend fun getArtPhotos(query: String,page: Int, queryPage: Int,orderBy: String, orientation: String): Output<SearchResults> {
        return try {
            val response = unsplashWs.getArtPhotos(query,page,queryPage,orientation)
            return Output.Success(response.body()!!)

        } catch (ex: NoInternetException) {
            ex.printStackTrace()
            Output.Error(ex)
        } catch (ex: JsonParseException) {
            ex.printStackTrace()
            Output.Error(ex)
        } catch (ex: Exception){
            ex.printStackTrace()
            Output.Error(ex)
        }
    }

    companion object {

        private var INSTANCE: GalleryArtRepositoryImpl? = null

        /**
         * Returns the single instance of this class, creating it if necessary.

         * @param tasksRemoteDataSource the backend data source
         * *
         * @param tasksLocalDataSource  the device storage data source
         * *
         * @return the [TasksRepository] instance
         */
        fun getInstance(unsplashWs: UnsplashWs): GalleryArtRepository {
            return INSTANCE ?: GalleryArtRepositoryImpl(unsplashWs)
                .apply { INSTANCE = this }
        }

        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        fun destroyInstance() {
            INSTANCE = null
        }
    }

}