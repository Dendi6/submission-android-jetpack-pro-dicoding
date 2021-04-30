package com.dendi.filmscatalogs.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseItem(
    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("results")
    val results: ArrayList<ListResponse>,

    @field:SerializedName("total_results")
    val totalResults: Int
)