package com.android.leivacourse.artapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.android.leivacourse.artapp.api.models.CoverPhoto
import com.android.leivacourse.artapp.data.local.model.ImageDetail
import com.android.leivacourse.artapp.utils.loadImage
import kotlinx.android.synthetic.main.activity_detail_art.*

class DetailArtActivity : AppCompatActivity() {
    companion object {
        const val PHOTO = "DetailArtActivity:photo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_art)
        setSupportActionBar(photoDetailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        with(intent.getParcelableExtra<ImageDetail>(PHOTO)) {
            this?.let {
                photoDetailToolbar.title = title
                photoDetailImage.loadUrl("${urls?.regular}")
                photoSummary.text = buildSpannedString {

                    bold { append("Descripción: ") }
                    appendln(description?:"N/A")

                    bold { append("Autor: ") }
                    appendln(user?.firstName?:"N/A")

                    bold { append("Localización: ") }
                    appendln(user?.location?:"N/A")

                    val mPrice= String.format("%.2f",getRamdomPrice)

                    bold { append("Precio: ") }
                    appendln("$mPrice MXN")

                }
                photoUser.loadUrl("${user?.profileImage?.small}")
                photoUserName.text=user?.name?:"N/A"
            }
        }

        btnPreview.setOnClickListener {
            val value:Any? =intent.getParcelableExtra<ImageDetail>(PHOTO)
            myStartActivity<CameraArtActivity>(bundleOf(PHOTO to value ))

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.detail_art_menu, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.menu_fav -> {
                Toast.makeText(this, "Se pulsa fav", Toast.LENGTH_LONG).show()
                true
            }
            else -> {
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                finish()
                super.onOptionsItemSelected(item)
            }
        }

}
