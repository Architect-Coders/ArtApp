package com.android.leivacourse.artapp.data.local.model

import androidx.room.*

@Dao
interface ImageDetailDao {

    @Query("SELECT * FROM ImageDetail")
    fun getAll(): List<ImageDetail>

    @Query("SELECT * FROM ImageDetail WHERE id = :id")
    fun findById(id: Int): ImageDetail

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertImages(images: List<ImageDetail>)

    @Update
    fun updateImage(image: ImageDetail)
}