package com.dendi.filmscatalogs.ui.detail

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dendi.filmscatalogs.data.source.remote.network.ApiConfig
import com.dendi.filmscatalogs.data.source.remote.response.DetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivityViewModel : ViewModel() {
    val dataDetail = MutableLiveData<DetailResponse>()

    fun setDetailMovies(id:Int){
        val client = ApiConfig.getApiService().detailMovies(id)
        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(call: Call<DetailResponse>, response: Response<DetailResponse>) {
                if (response.isSuccessful) {
                    dataDetail.postValue(response.body())
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun setDetailTv(id:Int){
        val client = ApiConfig.getApiService().detailTv(id)
        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(call: Call<DetailResponse>, response: Response<DetailResponse>) {
                if (response.isSuccessful) {
                    Log.d("berhasil",response.body().toString())
                    dataDetail.postValue(response.body())
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getDetail(): LiveData<DetailResponse> = dataDetail
}