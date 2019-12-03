package com.android.leivacourse.artapp.data.local.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlin.random.Random

@Parcelize
data class ImageDetail(
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
