package com.android.leivacourse.artapp

class ListaObrasPresenter(_view: ListaObrasContract.View) : ListaObrasContract.Presenter {

    private var mView : ListaObrasContract.View = _view
    private var mModel : ListaObrasContract.Model = ListaObrasModel(this)
}