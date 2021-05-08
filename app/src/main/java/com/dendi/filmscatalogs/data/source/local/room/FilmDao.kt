package com.dendi.filmscatalogs.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity

@Dao
interface FilmDao {
    @Query("SELECT * FROM listentities WHERE type = 'movies'")
    fun getMovies(): LiveData<List<ListEntity>>

    @Query("SELECT * FROM listentities WHERE type = 'tv'")
    fun getTvShow(): LiveData<List<ListEntity>>

    @Query("SELECT * FROM listentities WHERE favorited = 1")
    fun getFavorite(): LiveData<List<ListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(courses: List<ListEntity>)
}