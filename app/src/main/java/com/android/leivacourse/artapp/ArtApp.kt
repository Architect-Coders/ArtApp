package com.android.leivacourse.artapp

import android.app.Application


class ArtApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}