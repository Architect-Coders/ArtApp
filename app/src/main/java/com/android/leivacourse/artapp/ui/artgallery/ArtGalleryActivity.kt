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
import com.android.leivacourse.artapp.api.Retrofit
import com.android.leivacourse.artapp.data.DEFAULT_ORDER_BY
import com.android.leivacourse.artapp.data.DEFAULT_ORIENTATION
import com.android.leivacourse.artapp.data.DEFAULT_QUERY
import com.android.leivacourse.artapp.data.local.model.ImageDetail
import com.android.leivacourse.artapp.ui.detail.DetailArtActivity
import com.android.leivacourse.artapp.utils.NetworkConnectionInterceptor
import com.android.leivacourse.artapp.utils.changeLoaderStatus
import com.android.leivacourse.artapp.utils.myStartActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class ArtGalleryActivity : AppCompatActivity(), ArtGalleryContract.View{

    private lateinit var mPresenter : ArtGalleryPresenter
    private lateinit var mArtAdapter: ArtWorksAdapter

    private lateinit var lottieAnimation: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val networkInterceptor = NetworkConnectionInterceptor(WeakReference(this))
        val repo = GalleryArtRepositoryImpl.getInstance(Retrofit.getUnsplashService(networkInterceptor))
        mPresenter = ArtGalleryPresenter(repo,this)

        initComponents()

    }

    override fun onResume() {
        super.onResume()
        mPresenter.initLoader()
        mPresenter.getArtList(DEFAULT_QUERY,1,15, DEFAULT_ORDER_BY, DEFAULT_ORIENTATION)
    }

    private fun initComponents() {

        lottieAnimation = findViewById(R.id.loader_view)

        mArtAdapter = ArtWorksAdapter {
            myStartActivity<DetailArtActivity>(bundleOf(
                DetailArtActivity.PHOTO to it))
        }

        rv_arts.apply{
            adapter = mArtAdapter
            layoutManager = GridLayoutManager(context,2)
        }
    }

    override fun populateArts(items: List<ImageDetail>) {
        mArtAdapter.items = items
    }

    override fun showLoader() {
        changeLoaderStatus(lottieAnimation, VISIBLE)
    }

    override fun hideLoader() {
        changeLoaderStatus(lottieAnimation, GONE)

    }

    override fun errorMessage(message: String?) {
        message?.let {
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
        }
    }

    override fun setPresenter(presenter: Any?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        //it will be onDetach for the presenter
    }


}
