package com.android.leivacourse.artapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.leivacourse.artapp.data.local.model.ArtDetail
import com.android.leivacourse.artapp.common.Event
import com.android.leivacourse.artapp.utils.ToggleArtFavorite
import kotlinx.coroutines.*


class DetailArtViewModel(private val art: ArtDetail, private val toggleArtFavorite: ToggleArtFavorite): ViewModel(){


    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
    get() {
        if (_model.value == null) _model.value = UiModel(art)
        return _model
    }

    private val _navigation = MutableLiveData<Event<ArtDetail>>()
    val navigation: LiveData<Event<ArtDetail>> = _navigation

    class UiModel(val art: ArtDetail)

    fun favMenuSelected() {
        runBlocking {
            launch {
                _model.value?.art?.let {
                    _model.value = UiModel(toggleArtFavorite.invoke(it))
                }
            }
        }
    }

    fun checkFavorite() {
        runBlocking {
            launch {
                _model.value?.art?.let {
                    toggleArtFavorite.check(it)?.let{ artInDb->
                    _model.value = UiModel(artInDb)
                }

                }
            }
        }
    }


    fun onPreviewPushed(art: ArtDetail) {
        _navigation.value = Event(art)
    }

}