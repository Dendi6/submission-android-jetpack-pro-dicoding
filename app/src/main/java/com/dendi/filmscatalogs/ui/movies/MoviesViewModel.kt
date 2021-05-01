package com.dendi.filmscatalogs.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dendi.filmscatalogs.data.FilmRepository
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity

class MoviesViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    fun getMovies(): LiveData<List<ListEntity>> = filmRepository.getAllMovies()
}
