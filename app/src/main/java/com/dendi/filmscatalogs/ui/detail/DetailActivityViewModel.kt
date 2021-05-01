package com.dendi.filmscatalogs.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dendi.filmscatalogs.data.FilmRepository
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity

class DetailActivityViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    private var id: Int = 0

    fun setSelectedFilm(id: Int) {
        this.id = id
    }

    fun getMovies(): LiveData<ListEntity> = filmRepository.getDetailMovies(id)

    fun getTvShow(): LiveData<ListEntity> = filmRepository.getDetailTvShow(id)
}