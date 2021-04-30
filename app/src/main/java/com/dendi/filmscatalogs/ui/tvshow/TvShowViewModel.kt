package com.dendi.filmscatalogs.ui.tvshow

import android.content.ContentValues
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

class TvShowViewModel : ViewModel() {
    val listTv = MutableLiveData<ArrayList<ListResponse>>()

    fun setTv (){
        val client = ApiConfig.getApiService().getTv()
        client.enqueue(object : Callback<ResponseItem> {
            override fun onResponse(call: Call<ResponseItem>, response: Response<ResponseItem>) {
                if (response.isSuccessful) {
                    listTv.postValue(response.body()?.results)
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseItem>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getTv(): LiveData<ArrayList<ListResponse>> = listTv
}