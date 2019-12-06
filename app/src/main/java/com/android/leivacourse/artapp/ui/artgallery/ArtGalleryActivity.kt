package com.android.leivacourse.artapp.ui.artgallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.leivacourse.artapp.GalleryArtRepository
import com.android.leivacourse.artapp.GalleryArtRepositoryImpl
import com.android.leivacourse.artapp.DetailArtActivity
import com.android.leivacourse.artapp.R
import com.android.leivacourse.artapp.utils.replaceFragmentInActivity


class ArtGalleryActivity : AppCompatActivity() {

    private lateinit var listaobrasFragment : ArtGalleryFragment
    private lateinit var artGalleryPresenter : ArtGalleryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listaobrasFragment = supportFragmentManager
            .findFragmentById(R.id.content) as ArtGalleryFragment?
            ?: ArtGalleryFragment.newInstance().apply {
                arguments = Bundle().apply {
                }
            }.also { replaceFragmentInActivity(it, R.id.content) }

    }



}
