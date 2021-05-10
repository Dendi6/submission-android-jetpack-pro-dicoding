package com.dendi.filmscatalogs.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dendi.filmscatalogs.data.source.local.entity.DetailEntity
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.vo.Resource

interface FilmDataSource {

    fun getAllMovies(): LiveData<Resource<PagedList<ListEntity>>>

    fun getAllTvShow(): LiveData<Resource<PagedList<ListEntity>>>

    fun getFavorited(): LiveData<PagedList<ListEntity>>

    fun getDetailMovies(id: Int): LiveData<Resource<DetailEntity>>

    fun getDetailTvShow(id: Int): LiveData<Resource<DetailEntity>>

    fun setFilmFavorite(film: ListEntity, state: Boolean)
}