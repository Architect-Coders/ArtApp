package com.android.leivacourse.artapp.ui.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.leivacourse.artapp.data.local.model.ImageDetail

class CameraArtViewModel(private val art: ImageDetail):ViewModel(){

    private val _model = MutableLiveData<UiModel>()

    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) _model.value = UiModel(art)
            return _model
        }

    class UiModel(val art: ImageDetail)
}
