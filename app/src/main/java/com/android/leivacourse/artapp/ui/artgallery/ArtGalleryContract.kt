package com.android.leivacourse.artapp.ui.artgallery

import com.android.leivacourse.artapp.common.BasePresenter
import com.android.leivacourse.artapp.common.BaseView

interface ArtGalleryContract {

    interface View : BaseView{
        //TODO Agregar metodos a la interface
    }

    interface Presenter : BasePresenter {
        fun getListadoObras(page: Int, queryPage: Int,orderBy: String)
    }


}