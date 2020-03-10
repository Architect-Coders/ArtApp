package com.android.leivacourse.artapp.ui.camera

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraX
import androidx.camera.core.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.android.leivacourse.artapp.R
import com.android.leivacourse.artapp.ui.detail.DetailArtActivity.Companion.PHOTO
import com.android.leivacourse.artapp.data.local.model.ArtDetail
import com.android.leivacourse.artapp.utils.loadUrl
import com.android.leivacourse.artapp.utils.toast
import eu.bolt.screenshotty.*
import kotlinx.android.synthetic.main.activity_camera_art.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.io.File
import java.lang.IllegalStateException

class CameraArtActivity : AppCompatActivity() {
    private var screenshotManager: ScreenshotManager? = null
    private var subscription: ScreenshotResult.Subscription? = null
    private val REQUEST_CODE_PERMISSIONS = 999
    private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)

    private var contenData: View? = null

    private lateinit var art: ArtDetail
    private val viewModel: CameraArtViewModel by currentScope.viewModel(this) {
        parametersOf(art)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_art)

        art = intent.getParcelableExtra(PHOTO)
            ?: throw (IllegalStateException("Art not found"))

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = ""

        contenData = findViewById(R.id.contentData)

        initCamera()


        viewModel.model.observe(this, Observer(::onUpdateUI))

    }

    fun initCamera() {
        if (allPermissionsGranted()) {
            textureView.post {
                onCameraBindLivecycle(CameraXUtils.startCameraForCapture(textureView))
            }
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }
        // Every time the provided texture view changes, recompute layout
        textureView.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            CameraXUtils.updateTransform(textureView)
        }
    }

    @SuppressLint("RestrictedApi")
    fun onCameraBindLivecycle(preview: Preview) {
        CameraX.bindToLifecycle(this as LifecycleOwner, preview as CameraSelector)
    }


    fun onUpdateUI(model: CameraArtViewModel.UiModel) = with(model.art) {
        photoImagen.loadUrl("${urls?.regular}")
        photoDetail.setDetail(model.art)
        val mPrice = String.format("%.2f", getRamdomPrice)
        btnPrice.setText("$ $mPrice MXN")
    }


    fun onOpenChooserToShare(imagePath: File) {
        val uri = FileProvider.getUriForFile(this, this.packageName + ".fileprovider", imagePath)
        contenData?.visibility = View.VISIBLE
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
        shareIntent.type = "image/*"
        this.startActivity(Intent.createChooser(shareIntent, "Share Image"))

    }


    /**
     * Process result from permission request dialog box, has the request
     * been granted? If yes, start Camera. Otherwise display a toast
     */
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                textureView.post {
                    onCameraBindLivecycle(CameraXUtils.startCameraForCapture(textureView))
                }
            } else {
                toast("Permissions not granted by the user.")
                finish()
            }
        }
    }

    /**
     * Check if all permission specified in the manifest have been granted
     */
    private fun allPermissionsGranted(): Boolean {
        for (permission in REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(
                    this, permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_camera_art, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            android.R.id.home -> {
                finish()
                true
            }

            R.id.action_share -> {
                onActionShare()
                true
            }
            /* R.id.action_fav->{

                 return true
             }*/
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onActionShare() {
        contenData?.visibility = View.INVISIBLE
        screenshotManager = ScreenshotManagerBuilder(this).build()

        val screenshotResult = screenshotManager?.makeScreenshot()
        subscription = screenshotResult?.observe(

            onSuccess = {
                processScreenShot(it)
            },
            onError = {
                contenData?.visibility = View.VISIBLE
                toast("Error al realizar captura de pantalla.")
            }
        )
    }

    fun processScreenShot(screenshot: Screenshot) {
        val bitmap = when (screenshot) {
            is ScreenshotBitmap -> screenshot.bitmap
        }

        onOpenChooserToShare(CameraXUtils.saveBitmap(this, bitmap))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        screenshotManager?.onActivityResult(requestCode, resultCode, data)
    }


    override fun onDestroy() {
        subscription?.dispose()
        super.onDestroy()

    }
}

