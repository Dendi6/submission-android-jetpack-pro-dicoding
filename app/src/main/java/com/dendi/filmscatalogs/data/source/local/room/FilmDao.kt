package com.dendi.filmscatalogs.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity

@Dao
interface FilmDao {
    @Query("SELECT * FROM listentities")
    fun getItems(): LiveData<List<ListEntity>>

    @Query("SELECT * FROM listentities WHERE bookmarked = 1")
    fun getFavorite(): LiveData<List<ListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(courses: List<ListEntity>)
}