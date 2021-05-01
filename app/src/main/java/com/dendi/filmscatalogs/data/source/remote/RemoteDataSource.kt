package com.dendi.filmscatalogs.data.source.remote

import android.os.Handler
import android.os.Looper
import com.dendi.filmscatalogs.data.source.remote.response.ListResponse
import com.dendi.filmscatalogs.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {
    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getAllMovies(callback: LoadAllMoviesCallback) {
        handler.postDelayed({ callback.onAllMoviesReceived(jsonHelper.getMovies()) }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getAllTvShow(callback: LoadAllTvCallback) {
        handler.postDelayed({ callback.onAllTvShowReceived(jsonHelper.getTvShow()) }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadAllMoviesCallback {
        fun onAllMoviesReceived(moviesResponses: List<ListResponse>)
    }

    interface LoadAllTvCallback {
        fun onAllTvShowReceived(tvShowReponse: List<ListResponse>)
    }
}