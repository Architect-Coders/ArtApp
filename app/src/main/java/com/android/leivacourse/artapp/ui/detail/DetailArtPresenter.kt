package com.android.leivacourse.artapp.ui.detail

import com.android.leivacourse.artapp.data.local.model.ImageDetail

class DetailArtPresenter(
    private val mDetailArtView: DetailArtContract.View
) : DetailArtContract.Presenter {

    fun onCreate(art: ImageDetail?) {
        art?.let { mDetailArtView.updateUI(it) }
    }

    override fun previewPushed(){
        mDetailArtView.launchPreview()
    }

    override fun favMenuSelected(){
        mDetailArtView.selectFav()
    }
}