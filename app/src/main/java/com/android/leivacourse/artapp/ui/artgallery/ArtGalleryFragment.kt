package com.android.leivacourse.artapp.ui.artgallery



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.leivacourse.artapp.GalleryArtRepository
import com.android.leivacourse.artapp.GalleryArtRepositoryImpl
import com.android.leivacourse.artapp.R
import com.android.leivacourse.artapp.ui.artgallery.ArtGalleryContract.Presenter


class ArtGalleryFragment : Fragment(), ArtGalleryContract.View {

    private lateinit var mPresenter : ArtGalleryContract.Presenter


    companion object {
        fun newInstance(): ArtGalleryFragment = ArtGalleryFragment()

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root: View = inflater.inflate(R.layout.fragment_lista_obras, container, false)



        mPresenter = ArtGalleryPresenter( GalleryArtRepositoryImpl(GalleryArtRepository), this)
        return root
    }


    override fun onResume() {
        super.onResume()
        mPresenter.start()
    }


    override fun setPresenter(presenter: Any?) {
        mPresenter = checkNotNull(presenter as Presenter)
    }

}
