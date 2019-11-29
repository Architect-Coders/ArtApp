package com.android.leivacourse.artapp.ui.artgallery

import android.util.Log
import com.android.leivacourse.artapp.GalleryArtRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ArtGalleryPresenter(val mObrasRepository: GalleryArtRepository, val mArtGalleryView: ArtGalleryContract.View) : ArtGalleryContract.Presenter {

  //  private lateinit var mArtGalleryView : ArtGalleryContract.View
  //  private lateinit var mObrasRepository : GalleryArtRepository


    override fun start() {
        getListadoObras(1,1,"") //TODO cambiar parametros
    }

    override  fun getListadoObras(page: Int, queryPage: Int,orderBy: String){
        Log.wtf("YEHO", "presenter")

        GlobalScope.launch { withContext(Dispatchers.IO) {
            mObrasRepository.getArtPhotos(page,queryPage,orderBy) }

        }
    }
}