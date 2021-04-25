package com.dendi.filmscatalogs.data.source.remote.network

import com.dendi.filmscatalogs.data.source.remote.response.ResponseMovies
import com.dendi.filmscatalogs.data.source.remote.response.ResponseTv
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("trending/movie/week")
    fun getMovies(
        @Query("api_key") apiKey: String = "7376a113360dff63dacea32cf90f8e7a"
    ) : Call<ResponseMovies>

    @GET("trending/tv/week")
    fun getTvShows(
        @Query("api_key") apiKey: String = "7376a113360dff63dacea32cf90f8e7a"
    ) : Call<ResponseTv>
}