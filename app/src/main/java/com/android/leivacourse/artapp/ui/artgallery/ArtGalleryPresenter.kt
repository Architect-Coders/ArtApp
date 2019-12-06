package com.android.leivacourse.artapp.ui.artgallery

import com.android.leivacourse.artapp.GalleryArtRepository
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

    override fun getListadoObras(page: Int, queryPage: Int, orderBy: String) {
        GlobalScope.launch {
            val response = mObrasRepository.getArtPhotos(page, queryPage, orderBy)
            withContext(Dispatchers.Main) {

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