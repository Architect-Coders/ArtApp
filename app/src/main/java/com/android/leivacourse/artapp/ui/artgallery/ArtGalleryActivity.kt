package com.android.leivacourse.artapp.ui.artgallery

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.android.leivacourse.artapp.R
import com.android.leivacourse.artapp.api.Retrofit
import com.android.leivacourse.artapp.data.COL_NUM_FOR_MOBILE
import com.android.leivacourse.artapp.data.COL_NUM_FOR_TABLET
import com.android.leivacourse.artapp.ui.artgallery.ArtGalleryViewModel.GetArts
import com.android.leivacourse.artapp.ui.artgallery.ArtGalleryViewModel.UiModel
import com.android.leivacourse.artapp.ui.detail.DetailArtActivity
import com.android.leivacourse.artapp.utils.NetworkConnectionInterceptor
import com.android.leivacourse.artapp.utils.changeLoaderStatus
import com.android.leivacourse.artapp.utils.myStartActivity
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.lang.ref.WeakReference

class ArtGalleryActivity : AppCompatActivity(),
    FloatingSearchView.OnSearchListener {

    private lateinit var mArtAdapter: ArtWorksAdapter
    private lateinit var lottieAnimation: LottieAnimationView

    private val viewModel: ArtGalleryViewModel by currentScope.viewModel(this) {
        parametersOf(GetArts(GalleryArtRepositoryImpl.getInstance(
            Retrofit.getUnsplashService(NetworkConnectionInterceptor(WeakReference(this))))))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
    }

    fun updateUI(model: UiModel) {

        changeLoaderStatus(
            lottieAnimation,
            if (model is UiModel.Loading) View.VISIBLE else View.GONE
        )

        when (model) {
            is UiModel.Content -> {
                mArtAdapter.items = model.artWork
                if ( model.artWork.isNotEmpty())
                    mArtAdapter.items = model.artWork
                else
                    errorMessage(getString(R.string.no_results))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.model.observe(this, Observer(::updateUI))
    }

    private fun initComponents() {

        lottieAnimation = findViewById(R.id.loader_view)

        mArtAdapter = ArtWorksAdapter {
            myStartActivity<DetailArtActivity>(bundleOf(
                DetailArtActivity.PHOTO to it))
        }

        rv_arts.apply {
            adapter = mArtAdapter
            layoutManager = GridLayoutManager(context, if(resources.getBoolean(R.bool.isTablet)){
                COL_NUM_FOR_TABLET }
            else {
                COL_NUM_FOR_MOBILE
            })
        }
        sv_arts.setOnSearchListener(this)
    }

     fun errorMessage(message: String?) {
        message?.let {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSearchAction(currentQuery: String) {
     if (currentQuery.isNotEmpty())
         viewModel.loadDataByTitle(currentQuery)
    }

    override fun onSuggestionClicked(searchSuggestion: SearchSuggestion?) {
        // do nothing
    }


}
