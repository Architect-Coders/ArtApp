package com.android.leivacourse.artapp.data.local.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileImage(
	val small: String? = null,
	val large: String? = null,
	val medium: String? = null
):Parcelable
