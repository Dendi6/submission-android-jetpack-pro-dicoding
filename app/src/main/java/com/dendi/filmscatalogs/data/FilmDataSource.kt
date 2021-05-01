package com.dendi.filmscatalogs.data

import com.dendi.filmscatalogs.data.source.local.entity.ListEntity

interface FilmDataSource {

    fun getAllMovies(): List<ListEntity>

    fun getAllTvShow(): List<ListEntity>

    fun getDetailMovies(id: Int): ListEntity

    fun getDetailTvShow(id: Int): ListEntity
}