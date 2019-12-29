package com.android.leivacourse.artapp.ui.artgallery.gallery

import com.android.leivacourse.artapp.common.BasePresenter
import com.android.leivacourse.artapp.common.BaseView
import com.android.leivacourse.artapp.data.DEFAULT_ORDER_BY
import com.android.leivacourse.artapp.data.DEFAULT_ORIENTATION
import com.android.leivacourse.artapp.data.DEFAULT_QUERY
import com.android.leivacourse.artapp.data.local.model.ImageDetail

interface ArtGalleryContract {

    interface View : BaseView{
        fun populateArts(items: List<ImageDetail>)
        fun showLoader()
        fun hideLoader()
        fun errorMessage(message: String?)
    }

    interface Presenter : BasePresenter {
        fun setQuery(query: String)
        fun getArtList(page: Int = 1, queryPage: Int = 15, orderBy: String = DEFAULT_ORDER_BY, orientation: String = DEFAULT_ORIENTATION)
    }

}