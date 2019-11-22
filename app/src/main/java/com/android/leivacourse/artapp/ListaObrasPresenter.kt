package com.android.leivacourse.artapp

import com.android.leivacourse.artapp.data.ListadoObrasRepository


class ListaObrasPresenter(mObrasRepository: ListadoObrasRepository, mListaObrasView: ListaObrasContract.View) : ListaObrasContract.Presenter {

    private lateinit var mListaObrasView : ListaObrasContract.View
    private lateinit var mObrasRepository : ListadoObrasRepository

 /*   fun ListaObrasPresenter(obrasRepository: ListadoObrasRepository, obrasView: ListaObrasContract.View) {
        mObrasRepository = checkNotNull(obrasRepository, { "obrasRepository cannot be null" })
        mListaObrasView = checkNotNull(obrasView, {"obrasView cannot be null!"})
        mListaObrasView.setPresenter(this)
    }
*/
    override fun start() {
        getListadoObras()
    }

    override  fun getListadoObras(){
        mObrasRepository.obtenerObras()
    }
}