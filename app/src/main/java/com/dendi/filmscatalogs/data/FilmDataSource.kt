package com.dendi.filmscatalogs.data

import androidx.lifecycle.LiveData
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity

interface FilmDataSource {

    fun getAllMovies(): LiveData<List<ListEntity>>

    fun getAllTvShow(): LiveData<List<ListEntity>>

    fun getDetailMovies(id: Int): LiveData<ListEntity>

    fun getDetailTvShow(id: Int): LiveData<ListEntity>
}