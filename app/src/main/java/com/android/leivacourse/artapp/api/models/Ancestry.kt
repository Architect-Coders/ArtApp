package com.android.leivacourse.artapp.api.models

import com.google.gson.annotations.SerializedName

data class Ancestry(

	@field:SerializedName("type")
	val type: Type? = null,

	@field:SerializedName("category")
	val category: Category? = null,

	@field:SerializedName("subcategory")
	val subcategory: Subcategory? = null
)