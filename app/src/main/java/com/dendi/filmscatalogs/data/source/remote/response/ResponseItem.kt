package com.dendi.filmscatalogs.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseItem(
    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("results")
    val results: List<ListResponse>,

    @field:SerializedName("total_results")
    val totalResults: Int
)