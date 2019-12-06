package com.android.leivacourse.artapp.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ViewGroup.inflate(resId: Int) : View{
    return LayoutInflater.from(this.context).inflate(resId,this,false)
}

fun ImageView.loadImage(url: String){
    Glide.with(this).load(url).into(this)
}