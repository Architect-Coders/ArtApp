package com.android.leivacourse.artapp.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(resId: Int) : View{
    return LayoutInflater.from(this.context).inflate(resId,this,false)
}