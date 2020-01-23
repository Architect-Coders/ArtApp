package com.android.leivacourse.artapp.ui.camera

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import android.util.Rational
import android.util.Size
import android.view.Surface
import android.view.TextureView
import android.view.ViewGroup
import androidx.camera.core.Preview
import androidx.camera.core.PreviewConfig
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.leivacourse.artapp.data.local.model.ImageDetail
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class CameraArtViewModel(private val art: ImageDetail):ViewModel(){



    private val _model = MutableLiveData<UiModel>()

    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) _model.value = UiModel(art)
            return _model
        }


    class UiModel(val art: ImageDetail)
}

class CameraArtViewModelFactory(private val art: ImageDetail): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = CameraArtViewModel(art) as T
}