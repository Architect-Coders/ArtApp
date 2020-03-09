package com.android.leivacourse.artapp.data.local.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlin.random.Random

@Entity
@Parcelize
data class ImageDetail(
	@PrimaryKey(autoGenerate = true) val _id: Int,
	val urls: Urls? = null,
	val randomPrice: Double? = null,
	val description: String? = null,
	val id: String? = null,
	val user: User? = null
):Parcelable{
	val getRamdomPrice: Double
	get()  {
		return  Random.nextDouble(0.0, 500.0)
	}
}
