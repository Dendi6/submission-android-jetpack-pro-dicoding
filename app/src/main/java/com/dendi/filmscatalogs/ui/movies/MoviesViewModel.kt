package com.dendi.filmscatalogs.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dendi.filmscatalogs.data.FilmRepository
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.vo.Resource

class MoviesViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    fun getMovies(): LiveData<Resource<List<ListEntity>>> = filmRepository.getAllMovies()
}
