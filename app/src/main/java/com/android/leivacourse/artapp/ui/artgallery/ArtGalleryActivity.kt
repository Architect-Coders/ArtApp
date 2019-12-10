package com.android.leivacourse.artapp.ui.artgallery

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.android.leivacourse.artapp.*
import com.android.leivacourse.artapp.DetailArtActivity
import com.android.leivacourse.artapp.GalleryArtRepositoryImpl
import com.android.leivacourse.artapp.R
import com.android.leivacourse.artapp.api.Retrofit
import com.android.leivacourse.artapp.data.*
import com.android.leivacourse.artapp.data.local.model.ImageDetail
import com.android.leivacourse.artapp.myStartActivity
import com.android.leivacourse.artapp.utils.NetworkConnectionInterceptor
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class ArtGalleryActivity : AppCompatActivity(), ArtGalleryContract.View,
    FloatingSearchView.OnSearchListener {

    private lateinit var mPresenter : ArtGalleryPresenter
    private lateinit var mArtAdapter: ArtWorksAdapter

    private lateinit var lottieAnimation: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val networkInterceptor = NetworkConnectionInterceptor(WeakReference(this))
        val repo = GalleryArtRepositoryImpl.getInstance(Retrofit.getUnsplashService(networkInterceptor))
        mPresenter = ArtGalleryPresenter(repo, this)

        initComponents()
        getArtList(DEFAULT_QUERY)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.initLoader()
    }

    private fun getArtList(currentQuery: String?) =
        if (currentQuery != null)
            mPresenter.getArtList(currentQuery, DEFAULT_SEARCH_PAGE, QUERY_PAGE, DEFAULT_ORDER_BY, DEFAULT_ORIENTATION)
        else
            mPresenter.getArtList(DEFAULT_QUERY, DEFAULT_SEARCH_PAGE, QUERY_PAGE, DEFAULT_ORDER_BY, DEFAULT_ORIENTATION)

    private fun initComponents() {

        lottieAnimation = findViewById(R.id.loader_view)

        mArtAdapter = ArtWorksAdapter {
            myStartActivity<DetailArtActivity>(bundleOf(DetailArtActivity.PHOTO to it))
        }

        rv_arts.apply {
            adapter = mArtAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
        sv_arts.setOnSearchListener(this)
    }

    override fun populateArts(items: List<ImageDetail>) =
        if (items.isNotEmpty()) mArtAdapter.items =
            items else errorMessage(getString(R.string.no_results))

    override fun showLoader() {
        changeLoaderStatus(lottieAnimation, VISIBLE)
    }

    override fun hideLoader() {
        changeLoaderStatus(lottieAnimation, GONE)

    }

    override fun errorMessage(message: String?) {
        message?.let {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun setPresenter(presenter: Any?) { // Do nothing
    }

    override fun onDestroy() {
        super.onDestroy() // it will be onDetach for the presenter
    }

    override fun onSearchAction(currentQuery: String?) {
        getArtList(currentQuery)
    }

    override fun onSuggestionClicked(searchSuggestion: SearchSuggestion?) {
        // do nothing
    }
}
