package com.android.leivacourse.artapp.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

inline fun <reified T: AppCompatActivity> AppCompatActivity.myStartActivity(@Nullable bundle: Bundle? =null) {
    val intent = Intent(this, T::class.java)
    bundle?.let {
        intent.putExtras(bundle)
    }
    startActivity(intent)
}

fun AppCompatActivity.changeLoaderStatus(lottieAnimationView: LottieAnimationView, status: Int) {
    lottieAnimationView.visibility = status
}

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
