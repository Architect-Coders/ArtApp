package com.android.leivacourse.artapp.ui.artgallery.gallery

import com.android.leivacourse.artapp.data.DEFAULT_ORDER_BY
import com.android.leivacourse.artapp.data.DEFAULT_ORIENTATION
import com.android.leivacourse.artapp.data.DEFAULT_QUERY
import com.android.leivacourse.artapp.ui.artgallery.GalleryArtRepository
import com.android.leivacourse.artapp.ui.artgallery.toImageDetail
import com.android.leivacourse.artapp.utils.Output
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ArtGalleryPresenter(
    private val mObrasRepository: GalleryArtRepository,
    private var mArtGalleryView: ArtGalleryContract.View?) :
    ArtGalleryContract.Presenter {

    private var searchQuery = DEFAULT_QUERY

    override fun onDettach() {
        mArtGalleryView = null
    }

    override fun setQuery(query: String) {
        searchQuery = query
    }

    override fun getArtList(page: Int, queryPage: Int, orderBy: String, orientation: String) {
        mArtGalleryView?.showLoader()
        GlobalScope.launch {
            val response = mObrasRepository.getArtPhotos(searchQuery, page, queryPage, orderBy, orientation)
            withContext(Dispatchers.Main) {

                mArtGalleryView?.hideLoader()

                when (response){

                    is Output.Success -> {
                        mArtGalleryView?.populateArts(response.output.toImageDetail())
                    }

                    is Output.Error ->{
                        mArtGalleryView?.errorMessage(response.exception.message)
                    }

                }
            }
        }
    }


}