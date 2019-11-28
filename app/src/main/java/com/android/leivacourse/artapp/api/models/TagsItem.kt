package com.android.leivacourse.artapp.api.models

import com.google.gson.annotations.SerializedName

data class TagsItem(

	@field:SerializedName("source")
	val source: Source? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)