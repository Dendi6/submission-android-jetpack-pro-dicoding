package com.dendi.filmscatalogs.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dendi.filmscatalogs.data.source.local.entity.DetailEntity
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity

@Dao
interface FilmDao {
    @Query("SELECT * FROM listentities WHERE type = 'movies'")
    fun getMovies(): DataSource.Factory<Int, ListEntity>

    @Query("SELECT * FROM listentities WHERE type = 'tv'")
    fun getTvShow(): DataSource.Factory<Int, ListEntity>

    @Query("SELECT * FROM listentities WHERE favorited = 1")
    fun getFavorite(): DataSource.Factory<Int, ListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(films: List<ListEntity>)

    @Update
    fun updateFilm(films: ListEntity)

    @Query("SELECT * FROM detailentities WHERE id = :id")
    fun getDetailById(id: Int): LiveData<DetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailFilm(films: DetailEntity)
}