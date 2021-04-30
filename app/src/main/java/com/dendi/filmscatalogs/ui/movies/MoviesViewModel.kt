package com.dendi.filmscatalogs.ui.movies

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dendi.filmscatalogs.data.source.remote.network.ApiConfig
import com.dendi.filmscatalogs.data.source.remote.response.ListResponse
import com.dendi.filmscatalogs.data.source.remote.response.ResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel: ViewModel() {

    val listMovies = MutableLiveData<ArrayList<ListResponse>>()

    fun setMovies() {
        val client = ApiConfig.getApiService().getMovies()
        client.enqueue(object : Callback<ResponseItem> {
            override fun onResponse( call: Call<ResponseItem>, response: Response<ResponseItem>) {
                if (response.isSuccessful) {
                    listMovies.postValue(response.body()?.results)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseItem>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getMovies(): LiveData<ArrayList<ListResponse>> = listMovies
}
