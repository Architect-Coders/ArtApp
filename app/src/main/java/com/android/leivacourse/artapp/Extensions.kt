package com.android.leivacourse.artapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

inline fun <reified T: AppCompatActivity> Fragment.myStartActivity(@Nullable bundle: Bundle? =null) {
    val intent = Intent(context, T::class.java)
    bundle?.let {
        intent.putExtras(bundle)
    }
    startActivity(intent)
}

fun AppCompatActivity.changeLoaderStatus(lottieAnimationView: LottieAnimationView, status: Int) {
    lottieAnimationView.visibility = status
}

fun Fragment.changeLoaderStatus(lottieAnimationView: LottieAnimationView, status: Int) {
    lottieAnimationView.visibility = status
}

inline fun <reified T : Fragment> getInstance() : T{
    return T::class.java as T
}
