package com.android.leivacourse.artapp.api.models

import com.google.gson.annotations.SerializedName

data class ResultsItem(

	@field:SerializedName("current_user_collections")
	val currentUserCollections: List<Any?>? = null,

	@field:SerializedName("color")
	val color: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("liked_by_user")
	val likedByUser: Boolean? = null,

	@field:SerializedName("tags")
	val tags: List<TagsItem?>? = null,

	@field:SerializedName("urls")
	val urls: Urls? = null,

	@field:SerializedName("alt_description")
	val altDescription: Any? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("links")
	val links: Links? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("categories")
	val categories: List<Any?>? = null,

	@field:SerializedName("promoted_at")
	val promotedAt: Any? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("height")
	val height: Int? = null,

	@field:SerializedName("likes")
	val likes: Int? = null
)