package com.android.leivacourse.artapp

import com.android.leivacourse.artapp.api.models.SearchResults
import com.android.leivacourse.artapp.api.service.UnsplashWs
import com.android.leivacourse.artapp.api.models.NoInternetException
import com.android.leivacourse.artapp.utils.Output
import com.google.gson.JsonParseException
import retrofit2.Response

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

    override suspend fun getArtPhotos(
        query: String,
        page: Int,
        per_pge: Int,
        orientation: String
    ): Response<SearchResults> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        fun getInstance(unsplashWs: UnsplashWs/*tasksRemoteDataSource: TasksDataSource,
                        tasksLocalDataSource: TasksDataSource*/): GalleryArtRepository {
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