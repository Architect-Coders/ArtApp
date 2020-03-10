package com.android.leivacourse.artapp

import android.app.Application
import com.android.leivacourse.artapp.data.ArtRepository
import com.android.leivacourse.artapp.data.LocalDataSource
import com.android.leivacourse.artapp.data.local.ArtsDatabase
import com.android.leivacourse.artapp.data.local.RoomDataSource
import com.android.leivacourse.artapp.data.local.model.ArtDetail
import com.android.leivacourse.artapp.ui.artgallery.ArtGalleryActivity
import com.android.leivacourse.artapp.ui.artgallery.ArtGalleryViewModel
import com.android.leivacourse.artapp.ui.camera.CameraArtActivity
import com.android.leivacourse.artapp.ui.camera.CameraArtViewModel
import com.android.leivacourse.artapp.ui.detail.DetailArtActivity
import com.android.leivacourse.artapp.ui.detail.DetailArtViewModel
import com.android.leivacourse.artapp.utils.ToggleArtFavorite
import io.fabric.sdk.android.services.settings.IconRequest.build
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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
        modules(listOf(appModule, artGalleryModule, dataModule, DetailArtActivityModule, cameraArtModule))
    }
}

private val appModule = module {
    single { ArtsDatabase.build(get()) }
    factory<LocalDataSource> { RoomDataSource(get()) }
  //  factory<RemoteDataSource> { KitsuDataSource() }
    single<CoroutineDispatcher> { Dispatchers.Main }
}

private val dataModule = module {
    factory { ArtRepository(get()) }
}

private val artGalleryModule = module {
    scope(named<ArtGalleryActivity>()) {
        viewModel { (arts: ArtGalleryViewModel.GetArts) -> ArtGalleryViewModel(arts) }
    }
}

private val DetailArtActivityModule = module {
    scope(named<DetailArtActivity>()) {
        viewModel { (imgDetail: ArtDetail) -> DetailArtViewModel(imgDetail, get()) }
        scoped { ToggleArtFavorite(get()) }
    }
}

private val  cameraArtModule = module {
    scope(named<CameraArtActivity>()) {
        viewModel { (imgDetail: ArtDetail) -> CameraArtViewModel(imgDetail) }
    }
}

