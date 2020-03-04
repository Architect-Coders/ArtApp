package com.android.leivacourse.artapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.android.leivacourse.artapp.ui.camera.CameraArtActivity
import com.android.leivacourse.artapp.R
import com.android.leivacourse.artapp.data.local.model.ArtDetail
import com.android.leivacourse.artapp.ui.detail.DetailArtViewModel.UiModel
import com.android.leivacourse.artapp.utils.loadUrl
import com.android.leivacourse.artapp.utils.myStartActivity
import kotlinx.android.synthetic.main.activity_detail_art.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.lang.IllegalStateException

class DetailArtActivity : AppCompatActivity(){

    companion object {
        const val PHOTO = "DetailArtActivity:photo"
    }

    private lateinit var art: ArtDetail
    private val viewModel: DetailArtViewModel by currentScope.viewModel(this) {
        parametersOf(art)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_art)
        art = intent.getParcelableExtra(PHOTO) ?: throw (IllegalStateException("Art not found"))

        setSupportActionBar(photoDetailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        viewModel.model.observe(this, Observer(::updateUI))

        viewModel.navigation.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                myStartActivity<CameraArtActivity>(bundleOf(PHOTO to art ))
            }
        })

        btnPreview.setOnClickListener {
            viewModel.onPreviewPushed(art)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.detail_art_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.menu_fav -> {
                viewModel.favMenuSelected()

                true
            }
            else -> {
                finish()
                super.onOptionsItemSelected(item)
            }
        }

    private fun updateUI(model: UiModel) = with (model.art){
            photoDetailToolbar.title = title
            photoDetailImage.loadUrl("${urls?.regular}")
            photoSummary.setArt(model.art)
            photoUser.loadUrl("${user?.profileImage?.small}")
            photoUserName.text=user?.name?:"N/A"
        }

    }
