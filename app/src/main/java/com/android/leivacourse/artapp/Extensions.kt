package com.android.leivacourse.artapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
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