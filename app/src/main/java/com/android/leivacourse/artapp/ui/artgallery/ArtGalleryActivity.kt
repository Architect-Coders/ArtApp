package com.android.leivacourse.artapp.ui.artgallery

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.android.leivacourse.artapp.*
import com.android.leivacourse.artapp.api.Retrofit
import com.android.leivacourse.artapp.data.*
import com.android.leivacourse.artapp.ui.artgallery.ArtGalleryViewModel.*
import com.android.leivacourse.artapp.ui.detail.DetailArtActivity
import com.android.leivacourse.artapp.utils.NetworkConnectionInterceptor
import com.android.leivacourse.artapp.utils.changeLoaderStatus
import com.android.leivacourse.artapp.utils.myStartActivity
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class ArtGalleryActivity : AppCompatActivity(),
    FloatingSearchView.OnSearchListener {

    private lateinit var viewModel: ArtGalleryViewModel
    private lateinit var mArtAdapter: ArtWorksAdapter
    private lateinit var lottieAnimation: LottieAnimationView
    private lateinit var repo: GalleryArtRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val networkInterceptor = NetworkConnectionInterceptor(WeakReference(this))
        repo = GalleryArtRepositoryImpl.getInstance(Retrofit.getUnsplashService(networkInterceptor))

        viewModel = ViewModelProviders.of(this, ArtGalleryModelFactory(GetArts(repo),repo))[ArtGalleryViewModel::class.java]
        initComponents()


    }

    fun updateUI(model: UiModel) {

        changeLoaderStatus(
            lottieAnimation,
            if (model is UiModel.Loading) View.VISIBLE else View.GONE
        )

        when (model) {
            is UiModel.Content -> mArtAdapter.items = model.artWork
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.model.observe(this, Observer(::updateUI))
    }


 /*   private fun getArtList(currentQuery: String?) {
    //   viewModel.getArtList(DEFAULT_QUERY, DEFAULT_SEARCH_PAGE, QUERY_PAGE, DEFAULT_ORDER_BY, DEFAULT_ORIENTATION)
    viewModel = ViewModelProviders.of(this, ArtGalleryModelFactory(repo))[ArtGalleryViewModel::
    class.java]
}*/

    private fun initComponents() {

        lottieAnimation = findViewById(R.id.loader_view)

        mArtAdapter = ArtWorksAdapter {
            myStartActivity<DetailArtActivity>(bundleOf(
                DetailArtActivity.PHOTO to it))
        }

        rv_arts.apply {
            adapter = mArtAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
        sv_arts.setOnSearchListener(this)
    }

 /*   override fun populateArts(items: List<ImageDetail>) =
        if (items.isNotEmpty()) mArtAdapter.items =
            items else errorMessage(getString(R.string.no_results))


    override fun errorMessage(message: String?) {
        message?.let {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }


*/
    override fun onSearchAction(currentQuery: String?) {
     //   getArtList(currentQuery)
     if (currentQuery != null)
         viewModel.loadDataByTitle(currentQuery)
      //  viewModel.loadArtDataByTitle(currentQuery)
    }

    override fun onSuggestionClicked(searchSuggestion: SearchSuggestion?) {
        // do nothing
    }


}
