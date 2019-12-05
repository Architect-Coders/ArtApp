package com.android.leivacourse.artapp.data.local.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
	val profileImage: ProfileImage? = null,
	val name: String? = null,
	val lastName: String? = null,
	val location: String? = null,
	val id: String? = null,
	val firstName: String? = null,
	val username: String? = null
):Parcelable
