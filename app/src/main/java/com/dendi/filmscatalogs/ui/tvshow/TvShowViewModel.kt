package com.dendi.filmscatalogs.ui.tvshow

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dendi.filmscatalogs.data.source.remote.network.ApiConfig
import com.dendi.filmscatalogs.data.source.remote.response.ResultsTv
import com.dendi.filmscatalogs.data.source.remote.response.TvResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowViewModel : ViewModel() {
    val listTv = MutableLiveData<ArrayList<ResultsTv>>()

    fun setTv (){
        val client = ApiConfig.getApiService().getTv()
        client.enqueue(object : Callback<TvResponse> {
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                if (response.isSuccessful) {
                    listTv.postValue(response.body()?.results)
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getTv(): LiveData<ArrayList<ResultsTv>> = listTv
}