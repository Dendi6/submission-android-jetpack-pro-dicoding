package com.dendi.filmscatalogs.data.source.remote

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dendi.filmscatalogs.data.source.remote.network.ApiConfig
import com.dendi.filmscatalogs.data.source.remote.response.DetailResponse
import com.dendi.filmscatalogs.data.source.remote.response.ListResponse
import com.dendi.filmscatalogs.data.source.remote.response.ResponseItem
import com.dendi.filmscatalogs.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    fun getAllMovies(): LiveData<ApiResponse<List<ListResponse>>> {
        EspressoIdlingResource.increment()
        val resultFilm = MutableLiveData<ApiResponse<List<ListResponse>>>()

        //api request
        val client = ApiConfig.getApiService().getMovies()
        client.enqueue(object : Callback<ResponseItem> {
            override fun onResponse(
                call: Call<ResponseItem>,
                response: Response<ResponseItem>
            ) {
                if (response.isSuccessful) {
                    resultFilm.value = response.body()?.let { ApiResponse.success(it.results) }
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<ResponseItem>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })

        return resultFilm
    }

    fun getAllTvShow(): LiveData<ApiResponse<List<ListResponse>>> {
        EspressoIdlingResource.increment()
        val resultFilm = MutableLiveData<ApiResponse<List<ListResponse>>>()

        //api request
        val client = ApiConfig.getApiService().getTv()
        client.enqueue(object : Callback<ResponseItem> {
            override fun onResponse(
                call: Call<ResponseItem>,
                response: Response<ResponseItem>
            ) {
                if (response.isSuccessful) {
                    resultFilm.value = response.body()?.let { ApiResponse.success(it.results) }
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<ResponseItem>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })

        return resultFilm
    }

    fun getDetailMovies(id: Int, callback: LoadDetailMovieCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().detailMovies(id)
        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.onDetailMovieReceived(it) }
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getDetailTvShow(id: Int, callback: LoadDetailTvShowCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().detailTv(id)
        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.onDetailTvShowReceived(it) }
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    interface LoadAllTvCallback {
        fun onAllTvShowReceived(tvShowReponse: List<ListResponse>)
    }

    interface LoadDetailMovieCallback {
        fun onDetailMovieReceived(movieDetailResponse: DetailResponse)
    }

    interface LoadDetailTvShowCallback {
        fun onDetailTvShowReceived(tvShowDetailResponse: DetailResponse)
    }
}