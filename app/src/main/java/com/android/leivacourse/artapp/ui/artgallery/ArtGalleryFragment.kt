package com.android.leivacourse.artapp.ui.artgallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.android.leivacourse.artapp.*
import com.android.leivacourse.artapp.api.Retrofit
import com.android.leivacourse.artapp.data.DEFAULT_ORDER_BY
import com.android.leivacourse.artapp.data.DEFAULT_ORIENTATION
import com.android.leivacourse.artapp.data.DEFAULT_QUERY
import com.android.leivacourse.artapp.data.local.model.ImageDetail
import com.android.leivacourse.artapp.utils.NetworkConnectionInterceptor
import kotlinx.android.synthetic.main.fragment_lista_obras.*
import java.lang.ref.WeakReference

class ArtGalleryFragment : Fragment(), ArtGalleryContract.View {

    private lateinit var mPresenter : ArtGalleryPresenter
    private lateinit var mArtAdapter: ArtWorksAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lista_obras, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val networkInterceptor = NetworkConnectionInterceptor(WeakReference(context!!))
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

        mArtAdapter = ArtWorksAdapter {
            myStartActivity<DetailArtActivity>(bundleOf(DetailArtActivity.PHOTO to it))
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
        changeLoaderStatus(loader_view, View.VISIBLE)
    }

    override fun hideLoader() {
        changeLoaderStatus(loader_view, View.GONE)

    }

    override fun errorMessage(message: String?) {
        message?.let {
            Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun setPresenter(presenter: Any?) {

    }

    companion object {
        val TAG = "ArtGalleryFragment:TAG"
        fun newInstance(): ArtGalleryFragment = ArtGalleryFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        //it will be onDetach for the presenter
    }

}
