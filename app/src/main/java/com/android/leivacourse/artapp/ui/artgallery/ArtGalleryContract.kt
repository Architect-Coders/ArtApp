package com.android.leivacourse.artapp.ui.artgallery

import com.android.leivacourse.artapp.common.BasePresenter
import com.android.leivacourse.artapp.common.BaseView
import com.android.leivacourse.artapp.data.local.model.ImageDetail

interface ArtGalleryContract {

    interface View : BaseView{
        fun populateArts(items: List<ImageDetail>)
        fun errorMessage(message: String?)
    }

    interface Presenter : BasePresenter {
        fun getArtList(query:String , page: Int, queryPage: Int, orderBy: String, orientation: String)
    }

}