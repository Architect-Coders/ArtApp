package com.android.leivacourse.artapp.ui.artgallery.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.android.leivacourse.artapp.*
import com.android.leivacourse.artapp.api.Retrofit
import com.android.leivacourse.artapp.data.local.model.ImageDetail
import com.android.leivacourse.artapp.utils.NetworkConnectionInterceptor
import com.android.leivacourse.artapp.utils.toast
import kotlinx.android.synthetic.main.fragment_lista_obras.*
import java.lang.ref.WeakReference

class ArtGalleryFragment : Fragment(), ArtGalleryContract.View {

    private val mPresenter: ArtGalleryPresenter by lazy {
        val networkInterceptor = NetworkConnectionInterceptor(WeakReference(context!!))
        val repo =
            GalleryArtRepositoryImpl.getInstance(Retrofit.getUnsplashService(networkInterceptor))

        ArtGalleryPresenter(repo, this)
    }

    private val mArtAdapter: ArtWorksAdapter by lazy {
        ArtWorksAdapter {
            myStartActivity<DetailArtActivity>(bundleOf(DetailArtActivity.PHOTO to it))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lista_obras, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    override fun onResume() {
        super.onResume()
        mPresenter.getArtList()
    }

    private fun initComponents() {
        sv_arts.setOnSearchListener(ManageOnSearchListener(mPresenter))
        rv_arts.apply {
            adapter = mArtAdapter
            layoutManager = GridLayoutManager(context, 2)
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
        toast(message)
    }

    companion object {
        const val TAG = "ArtGalleryFragment:TAG"
        fun newInstance(): ArtGalleryFragment =
            ArtGalleryFragment()
    }

    override fun onDestroy() {
        mPresenter.onDettach()
        super.onDestroy()
    }

}
