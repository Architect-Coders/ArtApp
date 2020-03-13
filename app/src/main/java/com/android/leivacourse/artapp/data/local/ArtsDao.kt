package com.android.leivacourse.artapp.data.local

import androidx.room.*
import com.android.leivacourse.artapp.data.local.model.ArtDetail

@Dao
interface ArtsDao{
    @Query("SELECT * FROM ArtDetail")
    fun getAll(): List<ArtDetail>

    @Query("SELECT COUNT(id) FROM ArtDetail")
    fun artsCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg art: ArtDetail)

    @Delete
    fun remove(vararg art: ArtDetail)

}