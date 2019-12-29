package com.android.leivacourse.artapp.ui.artgallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.leivacourse.artapp.R
import com.android.leivacourse.artapp.ui.artgallery.fav.FavoriteFragment
import com.android.leivacourse.artapp.ui.artgallery.gallery.ArtGalleryFragment
import com.android.leivacourse.artapp.ui.artgallery.profile.ProfileFragment
import com.android.leivacourse.artapp.utils.replaceFragmentInActivity
import kotlinx.android.synthetic.main.activity_main.*

class ArtGalleryActivity : AppCompatActivity() {

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

}
