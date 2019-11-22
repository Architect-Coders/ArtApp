package com.android.leivacourse.artapp

import com.android.leivacourse.artapp.common.BasePresenter
import com.android.leivacourse.artapp.common.BaseView

interface ListaObrasContract {

    interface View : BaseView{
        //TODO Agregar metodos a la interface
    }

    interface Presenter : BasePresenter {
        fun getListadoObras()
    }


}