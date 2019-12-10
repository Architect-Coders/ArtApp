package com.android.leivacourse.artapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Rational
import android.util.Size
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraX
import androidx.camera.core.Preview
import androidx.camera.core.PreviewConfig
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.android.leivacourse.artapp.DetailArtActivity.Companion.PHOTO
import com.android.leivacourse.artapp.data.local.model.ImageDetail
import eu.bolt.screenshotty.*
import kotlinx.android.synthetic.main.activity_camera_art.*
import kotlinx.android.synthetic.main.activity_detail_art.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class CameraArtActivity : AppCompatActivity() {
    private var screenshotManager: ScreenshotManager?=null
    private var subscription:ScreenshotResult.Subscription?=null
    private val REQUEST_CODE_PERMISSIONS = 999
    private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_art)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title=""
        with(intent.getParcelableExtra<ImageDetail>(PHOTO)) {
            this?.let {
                photoImagen.loadUrl("${urls?.regular}")
                photoDetail.text = buildSpannedString {

                    bold { append("Descripción: ") }
                    appendln(description?:"N/A")

                    bold { append("Autor: ") }
                    appendln(user?.name?:"N/A")

                    bold { append("Localización: ") }
                    appendln(user?.location?:"N/A")

                }
                val mPrice= String.format("%.2f",getRamdomPrice)
                btnPrice.setText("$ $mPrice MXN")
            }
        }


        if (allPermissionsGranted()) {
            textureView.post {
                startCameraForCapture()
            }
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

        // Every time the provided texture view changes, recompute layout
        textureView.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            updateTransform()
        }
    }

    private fun startCameraForCapture() {
        //====================== Image Preview Config code Start==========================
        // Create configuration object for the viewfinder use case
        val previewConfig = PreviewConfig.Builder().apply {
            setTargetAspectRatio(Rational(1, 1))
            setTargetResolution(Size(640, 640))
        }.build()

        // Build the viewfinder use case
        val preview = Preview(previewConfig)

        // Every time the viewfinder is updated, recompute layout
        preview.setOnPreviewOutputUpdateListener {
            // To update the SurfaceTexture, we have to remove it and re-add it
            val parent = textureView.parent as ViewGroup
            parent.removeView(textureView)
            parent.addView(textureView, 0)
            textureView.surfaceTexture = it.surfaceTexture
            updateTransform()
        }
        //====================== Image Preview Config code End==========================
        CameraX.bindToLifecycle(this, preview) // For Preview
    }

    private fun updateTransform() {
        val matrix = Matrix()

        // Compute the center of the view finder
        val centerX = textureView.width / 2f
        val centerY = textureView.height / 2f

        // Correct preview output to account for display rotation
        val rotationDegree = when (textureView.display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }
        matrix.postRotate(-rotationDegree.toFloat(), centerX, centerY)

        // Finally, apply transformations to our TextureView
        textureView.setTransform(matrix)
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
                    startCameraForCapture()
                }
            } else {
                "Permissions not granted by the user.".toast()
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

    fun String.toast() {
        Toast.makeText(
            this@CameraArtActivity,
            this,
            Toast.LENGTH_SHORT
        ).show()
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
                 findViewById<View>(R.id.contentData).visibility=View.INVISIBLE
                 screenshotManager = ScreenshotManagerBuilder(this).build()

                val screenshotResult = screenshotManager?.makeScreenshot()
                   subscription = screenshotResult?.observe(
                    onSuccess = {
                        processScreenShot(it)
                    },
                    onError = {
                        findViewById<View>(R.id.contentData).visibility=View.VISIBLE
                        "Error al realizar captura de pantalla.".toast()}
                )
                true
            }
            /* R.id.action_fav->{

                 return true
             }*/
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun processScreenShot(screenshot: Screenshot) {
        val bitmap = when (screenshot) {
            is ScreenshotBitmap -> screenshot.bitmap
        }
        val uri= saveBitmap(bitmap)
        shareIt(uri)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        screenshotManager?.onActivityResult(requestCode, resultCode, data)
    }


    fun saveBitmap(bitmap: Bitmap?): Uri? {

        val wrapper = ContextWrapper(this)
        val file = wrapper.getDir("images", Context.MODE_PRIVATE)
        val imagePath = File(file, "image.jpeg")

        val fos: FileOutputStream
        try {
            fos = FileOutputStream(imagePath)

            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
        } catch (e: FileNotFoundException) {
            Log.e("GREC", e.message, e)
        } catch (e: IOException) {
            Log.e("GREC", e.message, e)
        }


        return FileProvider.getUriForFile(this, this.packageName + ".fileprovider", imagePath)

    }

    private fun shareIt(uri: Uri?) {
        try {
            findViewById<View>(R.id.contentData).visibility=View.VISIBLE
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
            shareIntent.type = "image/*"
            this.startActivity(Intent.createChooser(shareIntent, "Share Image"))

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        subscription?.dispose()
    }
}

