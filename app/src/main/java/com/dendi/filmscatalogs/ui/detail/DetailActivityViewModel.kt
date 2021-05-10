package com.dendi.filmscatalogs.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dendi.filmscatalogs.data.FilmRepository
import com.dendi.filmscatalogs.data.source.local.entity.DetailEntity
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.vo.Resource

class DetailActivityViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    private var id: Int = 0

    fun setSelectedFilm(id: Int) {
        this.id = id
    }

    fun getMovies(): LiveData<Resource<DetailEntity>> = filmRepository.getDetailMovies(id)

    fun getTvShow(): LiveData<Resource<DetailEntity>> = filmRepository.getDetailTvShow(id)

    fun setFavorite(filmEntity: ListEntity, newState: Boolean) =
        filmRepository.setFilmFavorite(filmEntity, newState)
}