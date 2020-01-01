package com.android.leivacourse.artapp.ui.detail

import com.android.leivacourse.artapp.data.local.model.ImageDetail

class DetailArtPresenter {
    private var view: View? = null

    interface View {
        fun updateUI(art: ImageDetail)
        fun launchPreview()
        fun selectFav()
    }

    fun onCreate(view: View, art: ImageDetail?) {
        this.view = view
        if (art != null) {
            view.updateUI(art)
        }
    }

    fun onDestroy() {
        view = null
    }

    fun previewPushed(){
        view?.launchPreview()
    }

    fun favMenuSelected(){
        view?.selectFav()
    }
}