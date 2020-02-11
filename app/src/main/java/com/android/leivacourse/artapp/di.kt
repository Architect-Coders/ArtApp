package com.android.leivacourse.artapp

import android.app.Application
import com.android.leivacourse.artapp.data.local.model.ImageDetail
import com.android.leivacourse.artapp.ui.artgallery.ArtGalleryActivity
import com.android.leivacourse.artapp.ui.artgallery.ArtGalleryViewModel
import com.android.leivacourse.artapp.ui.camera.CameraArtActivity
import com.android.leivacourse.artapp.ui.camera.CameraArtViewModel
import com.android.leivacourse.artapp.ui.detail.DetailArtActivity
import com.android.leivacourse.artapp.ui.detail.DetailArtViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(artGalleryModule, detailArtGalleryModule, cameraArtModule))
    }
}

private val artGalleryModule = module {
    scope(named<ArtGalleryActivity>()) {
        viewModel { (arts: ArtGalleryViewModel.GetArts) -> ArtGalleryViewModel(arts) }
    }
}

private val detailArtGalleryModule = module {
    scope(named<DetailArtActivity>()) {
        viewModel { (imgDetail: ImageDetail) -> DetailArtViewModel(imgDetail) }
    }
}

private val  cameraArtModule = module {
    scope(named<CameraArtActivity>()) {
        viewModel { (imgDetail: ImageDetail) -> CameraArtViewModel(imgDetail) }
    }
}

