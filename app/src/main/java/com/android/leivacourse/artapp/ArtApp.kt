package com.android.leivacourse.artapp

import android.app.Application
import androidx.room.Room
import com.android.leivacourse.artapp.data.local.ArtsDatabase


class ArtApp: Application() {

    lateinit var db: ArtsDatabase
    private set

    override fun onCreate() {
        super.onCreate()
        initDI()

        db = Room.databaseBuilder(
            this,
            ArtsDatabase::class.java,
            "arts-db"
        ).build()
    }
}