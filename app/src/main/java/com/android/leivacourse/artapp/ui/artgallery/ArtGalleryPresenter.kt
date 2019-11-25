package com.android.leivacourse.artapp.ui.artgallery

import android.util.Log
import com.android.leivacourse.artapp.GalleryArtRepository


class ArtGalleryPresenter(mObrasRepository: GalleryArtRepository, mArtGalleryView: ArtGalleryContract.View) : ArtGalleryContract.Presenter {

    private lateinit var mArtGalleryView : ArtGalleryContract.View
    private lateinit var mObrasRepository : GalleryArtRepository


    override fun start() {
        getListadoObras(1,1,"") //TODO cambiar parametros
    }

    override  fun getListadoObras(page: Int, queryPage: Int,orderBy: String){
        Log.wtf("YEHO", "presenter")

        //mObrasRepository.getArtPhotos(page, queryPage,orderBy) //TODO meter la corutina para la llamada del repositorio
    }
}