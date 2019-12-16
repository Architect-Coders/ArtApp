package com.android.leivacourse.artapp.ui.artgallery

import com.android.leivacourse.artapp.utils.Output
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ArtGalleryPresenter(
    private val mObrasRepository: GalleryArtRepository,
    private val mArtGalleryView: ArtGalleryContract.View) : ArtGalleryContract.Presenter {

    override fun start() {
        //getListadoObras(1, 1, "") //TODO cambiar parametros
    }

    override fun initLoader() {
        mArtGalleryView.showLoader()
    }

    override fun getArtList(query:String , page: Int, queryPage: Int, orderBy: String, orientation: String) {
        GlobalScope.launch {
            val response = mObrasRepository.getArtPhotos(query, page, queryPage, orderBy, orientation)
            withContext(Dispatchers.Main) {

                mArtGalleryView.hideLoader()

                when (response){

                    is Output.Success -> {
                        mArtGalleryView.populateArts(response.output.toImageDetail())
                    }

                    is Output.Error ->{
                        mArtGalleryView.errorMessage(response.exception.message)
                    }

                }
            }
        }
    }


}