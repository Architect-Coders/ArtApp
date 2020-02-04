package com.android.leivacourse.artapp.ui.artgallery

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.android.leivacourse.artapp.R
import com.android.leivacourse.artapp.api.Retrofit
import com.android.leivacourse.artapp.data.*
import com.android.leivacourse.artapp.data.local.model.ImageDetail
import com.android.leivacourse.artapp.ui.artgallery.fav.FavoriteFragment
import com.android.leivacourse.artapp.ui.artgallery.gallery.ArtGalleryContract
import com.android.leivacourse.artapp.ui.artgallery.gallery.ArtGalleryFragment
import com.android.leivacourse.artapp.ui.artgallery.gallery.ArtWorksAdapter
import com.android.leivacourse.artapp.ui.artgallery.profile.ProfileFragment
import com.android.leivacourse.artapp.ui.detail.DetailArtActivity
import com.android.leivacourse.artapp.utils.NetworkConnectionInterceptor
import com.android.leivacourse.artapp.utils.changeLoaderStatus
import com.android.leivacourse.artapp.utils.myStartActivity
import com.android.leivacourse.artapp.utils.replaceFragmentInActivity
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_lista_obras.*
import java.lang.ref.WeakReference


class ArtGalleryActivity : AppCompatActivity(){

    private var fragment: Fragment? = null
    private var tag: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragmentInActivity(
            ArtGalleryFragment.newInstance(), R.id.container_fragments,
            ArtGalleryFragment.TAG)
        setupNavigation()
    }


    private fun setupNavigation() {
        nav_bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_arts -> {
                    fragment = ArtGalleryFragment.newInstance()
                    tag = ArtGalleryFragment.TAG
                }
                R.id.action_buy -> {
                    fragment = FavoriteFragment.newInstance()
                    tag = FavoriteFragment.TAG
                }
                R.id.action_profile -> {
                    fragment = ProfileFragment.newInstance()
                    tag = ProfileFragment.TAG
                }
            }
            replaceFragmentInActivity(fragment, R.id.container_fragments,tag)
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //it will be onDetach for the presenter
    }


}
