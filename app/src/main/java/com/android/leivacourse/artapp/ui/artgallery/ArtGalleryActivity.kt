package com.android.leivacourse.artapp.ui.artgallery

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.leivacourse.artapp.DetailArtActivity
import com.android.leivacourse.artapp.GalleryArtRepositoryImpl
import com.android.leivacourse.artapp.R
import com.android.leivacourse.artapp.api.Retrofit
import com.android.leivacourse.artapp.data.local.model.ImageDetail
import kotlinx.android.synthetic.main.activity_main.*


class ArtGalleryActivity : AppCompatActivity(), ArtGalleryContract.View{

    private lateinit var mPresenter : ArtGalleryPresenter
    private lateinit var mArtAdapter: ArtWorksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo = GalleryArtRepositoryImpl(Retrofit.getUnsplashService())
        mPresenter = ArtGalleryPresenter(repo,this)

        initComponents()

    }

    override fun onResume() {
        super.onResume()
        mPresenter.getListadoObras(1,15,"latest")
    }

    private fun initComponents() {

        mArtAdapter = ArtWorksAdapter {
            val intent = Intent(this,DetailArtActivity::class.java)
            intent.putExtras(bundleOf(DetailArtActivity.PHOTO to it))
            startActivity(intent)
        }

        with(rv_arts){
            adapter = mArtAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun populateArts(items: List<ImageDetail>) {
        mArtAdapter.items = items
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
