package com.android.leivacourse.artapp.data.local.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.android.leivacourse.artapp.data.local.Converters
import kotlinx.android.parcel.Parcelize
import kotlin.random.Random

@Entity
@Parcelize
@TypeConverters(Converters::class)
data class ArtDetail(
	@PrimaryKey(autoGenerate = true)
	val id : Int?,
	@ColumnInfo(name = "urls") val urls: Urls? = null,
	@ColumnInfo(name = "price") val randomPrice: Double? = null,
	@ColumnInfo(name = "description") val description: String? = null,
	@ColumnInfo(name = "user") val user: User? = null,
	@ColumnInfo(name = "favorite") var favorite: Boolean = false
):Parcelable{
	val getRamdomPrice: Double
	get()  {
		return  Random.nextDouble(0.0, 500.0)
	}
}
