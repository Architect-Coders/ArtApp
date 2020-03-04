package com.android.leivacourse.artapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.leivacourse.artapp.data.local.model.ArtDetail

@Database(entities = [ArtDetail::class], version = 1)
abstract class ArtsDatabase : RoomDatabase() {
    abstract fun artsDao() : ArtsDao
}

