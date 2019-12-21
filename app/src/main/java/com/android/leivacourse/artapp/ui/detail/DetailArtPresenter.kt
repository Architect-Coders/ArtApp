package com.android.leivacourse.artapp.ui.detail

import com.android.leivacourse.artapp.data.local.model.ImageDetail
import com.android.leivacourse.artapp.data.model.Obra

class DetailArtPresenter {
    private var view: View? = null

    interface View {
        fun updateUI(art: ImageDetail)
    }

    fun onCreate(view: View, art: ImageDetail) {
        this.view = view
        view.updateUI(art)
    }

    fun onDestroy() {
        view = null
    }
}