package com.android.leivacourse.artapp.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(message: String?,length: Int = Toast.LENGTH_SHORT){
    message?.let {
        Toast.makeText(context,message,length).show()
    }
}