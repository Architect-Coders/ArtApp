package com.android.leivacourse.artapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.leivacourse.artapp.data.local.model.ArtDetail

@Database(entities = arrayOf(ArtDetail::class), version = 1, exportSchema = false)
abstract class ArtsDatabase : RoomDatabase() {
    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            ArtsDatabase::class.java,
            "arts-db"
        ).build()
    }

    abstract fun artsDao() : ArtsDao
}
