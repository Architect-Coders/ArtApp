package com.android.leivacourse.artapp.ui.detail

import com.android.leivacourse.artapp.data.local.model.ImageDetail

interface DetailArtContract {
    interface View {
        fun updateUI(art: ImageDetail)
        fun launchPreview()
        fun selectFav()
    }

    interface Presenter {
        fun previewPushed()
        fun favMenuSelected()
    }
}