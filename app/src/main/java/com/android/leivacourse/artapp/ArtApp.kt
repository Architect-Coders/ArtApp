package com.android.leivacourse.artapp

import android.app.Application
import com.android.leivacourse.artapp.data.local.Database
import androidx.room.Room

class ArtApp : Application() {

    lateinit var db: Database
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            this,
            Database::class.java, "art-db"
        ).build()
    }
}