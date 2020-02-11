package com.android.leivacourse.artapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.leivacourse.artapp.data.local.model.ImageDetail
import com.android.leivacourse.artapp.common.Event

class DetailArtViewModel(private val art: ImageDetail): ViewModel(){

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
    get() {
        if (_model.value == null) _model.value = UiModel(art)
        return _model
    }

    private val _navigation = MutableLiveData<Event<ImageDetail>>()
    val navigation: LiveData<Event<ImageDetail>> = _navigation

    class UiModel(val art: ImageDetail)

    fun favMenuSelected(){
        TODO()
    }

    fun onPreviewPushed(art: ImageDetail) {
        _navigation.value = Event(art)
    }

}