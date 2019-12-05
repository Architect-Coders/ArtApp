package com.android.leivacourse.artapp.api.models

import com.google.gson.annotations.SerializedName

data class SearchResults(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null
)