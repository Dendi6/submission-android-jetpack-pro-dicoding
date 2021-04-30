package com.dendi.filmscatalogs.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("results")
    val results: ArrayList<ResultsMovies>,

    @field:SerializedName("total_results")
    val totalResults: Int
)

data class ResultsMovies(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("overview")
    val overview: String,


    )
