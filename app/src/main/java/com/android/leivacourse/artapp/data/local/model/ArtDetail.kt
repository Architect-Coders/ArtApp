package com.android.leivacourse.artapp.data.local.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlin.random.Random

@Entity
@Parcelize
data class ArtDetail(
	@PrimaryKey val id: String? = null,
	@ColumnInfo(name = "urls") val urls: Urls? = null,
	@ColumnInfo(name = "price") val randomPrice: Double? = null,
	@ColumnInfo(name = "description") val description: String? = null,
	@ColumnInfo(name = "user") val user: User? = null,
	@ColumnInfo(name = "favorite") val favorite: Boolean = false
):Parcelable{
	val getRamdomPrice: Double
	get()  {
		return  Random.nextDouble(0.0, 500.0)
	}
}
