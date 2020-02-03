package com.android.leivacourse.artapp.ui.artgallery.fav
import com.android.leivacourse.artapp.common.BaseView

interface FavoriteItemContract {

    interface View : BaseView {
        fun showLoader()
        fun hideLoader()

    }


}