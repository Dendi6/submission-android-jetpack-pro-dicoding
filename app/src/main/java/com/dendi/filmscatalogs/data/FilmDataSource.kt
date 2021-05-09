package com.dendi.filmscatalogs.data

import androidx.lifecycle.LiveData
import com.dendi.filmscatalogs.data.source.local.entity.DetailEntity
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.vo.Resource

interface FilmDataSource {

    fun getAllMovies(): LiveData<Resource<List<ListEntity>>>

    fun getAllTvShow(): LiveData<Resource<List<ListEntity>>>

    fun getFavorited(): LiveData<List<ListEntity>>

    fun getDetailMovies(id: Int): LiveData<Resource<DetailEntity>>

    fun getDetailTvShow(id: Int): LiveData<Resource<DetailEntity>>
}