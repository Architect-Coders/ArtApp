package com.android.leivacourse.artapp.data.local

import androidx.room.RoomDatabase
import androidx.room.Database
import com.android.leivacourse.artapp.data.local.model.ImageDetail
import com.android.leivacourse.artapp.data.local.model.ImageDetailDao

@Database(entities = [ImageDetail::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun imageDetailDao(): ImageDetailDao
}

