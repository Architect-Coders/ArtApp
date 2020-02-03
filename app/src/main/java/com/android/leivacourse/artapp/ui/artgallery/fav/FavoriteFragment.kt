package com.android.leivacourse.artapp.ui.artgallery.fav


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.lottie.LottieAnimationView

import com.android.leivacourse.artapp.R
import com.android.leivacourse.artapp.ui.artgallery.gallery.ArtWorksAdapter
import com.android.leivacourse.artapp.ui.artgallery.gallery.ManageOnSearchListener
import com.android.leivacourse.artapp.ui.detail.DetailArtActivity
import com.android.leivacourse.artapp.utils.myStartActivity
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_lista_obras.*
import kotlinx.android.synthetic.main.fragment_lista_obras.loader_view

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() ,FavoriteItemContract.View {

    private lateinit var lottieAnimation: LottieAnimationView
    private val mPresenterFav: FavoritePresenter?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    private val mFavoriteAdapter: FavoriteItemAdapter by lazy {
        FavoriteItemAdapter {
            myStartActivity<DetailArtActivity>(bundleOf(DetailArtActivity.PHOTO to it))
        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponets()
    }

    private fun initComponets() {
        lottieAnimation = loader_view
        rv_favorite_items.apply {
            adapter = mFavoriteAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    companion object{
        val TAG = "FavoriteFragment:TAG"
        fun newInstance() = FavoriteFragment()

    }

    override fun showLoader() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoader() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
