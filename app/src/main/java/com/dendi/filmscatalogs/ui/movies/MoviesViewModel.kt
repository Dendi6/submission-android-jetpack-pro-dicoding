package com.dendi.filmscatalogs.ui.movies

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dendi.filmscatalogs.data.source.local.entity.MoviesEntity
import com.dendi.filmscatalogs.data.source.remote.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel : ViewModel() {
//    private val _listReview = MutableLiveData<List<ResultsItem>>()
//    val listReview: LiveData<List<ResultsItem>> = _listReview
//
//    init {
//        getMovies()
//    }

    private val listMovies = MutableLiveData<List<MoviesEntity>>()

    fun setMovies() {
        ApiConfig.getApiService().getMovies()
            .enqueue(object : Callback<List<MoviesEntity>> {
                override fun onResponse(
                    call: Call<List<MoviesEntity>>,
                    response: Response<List<MoviesEntity>>
                ) {
                    if (response.isSuccessful) {
                        listMovies.postValue(response.body())
                        Log.d("info", response.body().toString())
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }

                }

                override fun onFailure(call: Call<List<MoviesEntity>>, t: Throwable) {
                }
            })
    }

//    fun getUsers(): LiveData<List<MoviesEntity>> = listMovies

//    fun getMovies() {
//        val client = ApiConfig.getApiService().getMovies()
//        client.enqueue(object : Callback<MovieResponse> {
//            override fun onResponse(
//                call: Call<MovieResponse>,
//                response: Response<MovieResponse>
//            ) {
//                if (response.isSuccessful) {
//                    _listReview.value = response.body()?.results
//                } else {
//                    Log.e(TAG, "onFailure: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
//                Log.e(TAG, "onFailure: ${t.message.toString()}")
//            }
//        })
//    }
}
