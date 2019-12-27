package com.android.leivacourse.artapp.ui.artgallery

import com.android.leivacourse.artapp.GalleryArtRepository
import com.android.leivacourse.artapp.data.DEFAULT_ORDER_BY
import com.android.leivacourse.artapp.data.DEFAULT_ORIENTATION
import com.android.leivacourse.artapp.data.DEFAULT_QUERY
import com.android.leivacourse.artapp.utils.Output
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ArtGalleryPresenter(
    private val mObrasRepository: GalleryArtRepository,
    private var mArtGalleryView: ArtGalleryContract.View?) : ArtGalleryContract.Presenter {

    init {
        getArtList(DEFAULT_QUERY, 1, 15, DEFAULT_ORDER_BY, DEFAULT_ORIENTATION)
    }

    override fun onDettach() {
        mArtGalleryView = null
    }

    override fun getArtList(query:String , page: Int, queryPage: Int, orderBy: String, orientation: String) {
        mArtGalleryView?.showLoader()
        GlobalScope.launch {
            val response = mObrasRepository.getArtPhotos(query, page, queryPage, orderBy, orientation)
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