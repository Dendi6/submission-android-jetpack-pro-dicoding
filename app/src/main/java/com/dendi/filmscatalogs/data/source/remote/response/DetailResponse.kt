package com.dendi.filmscatalogs.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Float,

	@field:SerializedName("overview")
	val overview: String? = null,
)
