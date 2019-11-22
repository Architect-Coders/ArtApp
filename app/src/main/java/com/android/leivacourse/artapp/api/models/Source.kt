package com.android.leivacourse.artapp.api.models

import com.android.leivacourse.artapp.api.models.Ancestry
import com.android.leivacourse.artapp.api.models.CoverPhoto
import com.google.gson.annotations.SerializedName

data class Source(

	@field:SerializedName("meta_description")
	val metaDescription: String? = null,

	@field:SerializedName("ancestry")
	val ancestry: Ancestry? = null,

	@field:SerializedName("cover_photo")
	val coverPhoto: CoverPhoto? = null,

	@field:SerializedName("meta_title")
	val metaTitle: String? = null,

	@field:SerializedName("subtitle")
	val subtitle: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)