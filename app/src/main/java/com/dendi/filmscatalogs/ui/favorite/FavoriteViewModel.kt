package com.dendi.filmscatalogs.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dendi.filmscatalogs.data.FilmRepository
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity

class FavoriteViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    fun getFavorite(): LiveData<PagedList<ListEntity>> = filmRepository.getFavorited()

    fun setFavorited(filmsEntity: ListEntity) {
        val newState = !filmsEntity.favorited
        filmRepository.setFilmFavorite(filmsEntity, newState)
    }
}