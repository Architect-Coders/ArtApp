package com.android.leivacourse.artapp.ui.camera

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import android.util.Rational
import android.util.Size
import android.view.Surface
import android.view.TextureView
import android.view.ViewGroup
import androidx.camera.core.Preview
import androidx.camera.core.PreviewConfig
import com.android.leivacourse.artapp.data.local.model.ImageDetail
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class CameraArtPresenter : CameraArtContract.Presenter {

    companion object {
        const val TAG = "CameraArt"
        const val DIR_NAME = "images"
        const val RESOURCE_NAME = "image.jpeg"

    }

    private var view: CameraArtContract.View? = null

    override fun onCreate(view: CameraArtContract.View, photo: ImageDetail?) {
        this.view = view
        view.initCamera()
        photo?.let { view.updateUI(it) }
    }

    override fun startCameraForCapture(textureView: TextureView) {
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
            updateTransform(textureView)
        }
        //====================== Image Preview Config code End==========================

        view?.cameraBindLivecycle(preview)
    }

    fun updateTransform(textureView: TextureView) {
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
        textureView.setTransform(matrix)

    }


    override fun saveBitmap(context: Context, bitmap: Bitmap) {

        val wrapper = ContextWrapper(context)
        val file = wrapper.getDir(DIR_NAME, Context.MODE_PRIVATE)
        val imagePath = File(file, RESOURCE_NAME)
        val fos: FileOutputStream
        try {
            fos = FileOutputStream(imagePath)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
        } catch (e: FileNotFoundException) {
            Log.e(TAG, e.message, e)
        } catch (e: IOException) {
            Log.e(TAG, e.message, e)
        }

        view?.shareImage(imagePath)
    }

    override fun onDestroy() {
       view=null
    }


}