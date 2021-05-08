package com.dendi.filmscatalogs.data.source.local

import androidx.lifecycle.LiveData
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.data.source.local.room.FilmDao

class LocalDataSource private constructor(private val mFilmDao: FilmDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao: FilmDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao).apply { INSTANCE = this }
    }

    fun getMovies(): LiveData<List<ListEntity>> = mFilmDao.getMovies()

    fun getTvShow() : LiveData<List<ListEntity>> = mFilmDao.getTvShow()

    fun insertFilm(film: List<ListEntity>) = mFilmDao.insertFilm(film)
}