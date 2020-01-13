package com.android.leivacourse.artapp.ui.camera
import android.content.Context
import android.graphics.Bitmap
import android.view.TextureView
import androidx.camera.core.Preview
import com.android.leivacourse.artapp.data.local.model.ImageDetail
import java.io.File


interface CameraArtContract {
    interface View{
       fun updateUI(photo: ImageDetail)
       fun initCamera()
       fun cameraBindLivecycle(preview:Preview)
        fun shareImage(imagePath: File)
    }

    interface Presenter{
       fun onCreate(view:View, photo: ImageDetail?)
        fun startCameraForCapture(textureView: TextureView)
        fun saveBitmap(context: Context, bitmap:Bitmap)
        fun onDestroy()
    }
}