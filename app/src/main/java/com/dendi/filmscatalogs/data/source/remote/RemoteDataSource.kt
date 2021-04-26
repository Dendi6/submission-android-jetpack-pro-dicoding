package com.dendi.filmscatalogs.data.source.remote

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.dendi.filmscatalogs.data.source.remote.network.ApiConfig
import com.dendi.filmscatalogs.data.source.remote.response.MovieResponse
import com.dendi.filmscatalogs.data.source.remote.response.ResultsItem
import org.json.JSONException
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

    fun getTrendingMovies():List<ResultsItem>{
        var list = ArrayList<ResultsItem>()
        try {
            val client = ApiConfig.getApiService().getMovies()
            client.enqueue(object : Callback<MovieResponse> {
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    if (response.isSuccessful) {
                        Log.d("Item","Berhasil : ${response.body()}")
                        list = response.body()?.results as ArrayList<ResultsItem>
                    } else {
                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                }
            })
        }catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }
}