package com.android.leivacourse.artapp.api.models

import com.google.gson.annotations.SerializedName

data class Type(

	@field:SerializedName("pretty_slug")
	val prettySlug: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)