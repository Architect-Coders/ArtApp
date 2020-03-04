package com.android.leivacourse.artapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.leivacourse.artapp.data.local.model.ArtDetail

@Dao
interface ArtsDao{
    @Query("SELECT * FROM ArtDetail")
    fun getAll(): List<ArtDetail>

    @Query("SELECT COUNT(id) FROM ArtDetail")
    fun artsCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg art: ArtDetail)

}