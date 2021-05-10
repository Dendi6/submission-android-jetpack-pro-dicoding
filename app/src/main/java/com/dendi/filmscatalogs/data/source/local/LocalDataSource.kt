package com.dendi.filmscatalogs.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dendi.filmscatalogs.data.source.local.entity.DetailEntity
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.data.source.local.room.FilmDao

class LocalDataSource private constructor(private val mFilmDao: FilmDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao: FilmDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao).apply { INSTANCE = this }
    }

    fun getMovies(): DataSource.Factory<Int, ListEntity> = mFilmDao.getMovies()

    fun getTvShow(): DataSource.Factory<Int, ListEntity> = mFilmDao.getTvShow()

    fun getFavorited(): DataSource.Factory<Int, ListEntity> = mFilmDao.getFavorite()

    fun insertFilm(film: List<ListEntity>) = mFilmDao.insertFilm(film)

    fun getDetailById(id: Int): LiveData<DetailEntity> = mFilmDao.getDetailById(id)

    fun insertDetail(film: DetailEntity) = mFilmDao.insertDetailFilm(film)

    fun setFilmFavorite(film: ListEntity, newState: Boolean) {
        film.favorited = newState
        mFilmDao.updateFilm(film)
    }
}