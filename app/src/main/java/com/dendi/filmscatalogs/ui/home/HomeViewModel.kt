package com.dendi.filmscatalogs.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dendi.filmscatalogs.data.FilmRepository
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.vo.Resource

class HomeViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    fun getMovies(): LiveData<Resource<List<ListEntity>>> = filmRepository.getAllMovies()

    fun getTvShow(): LiveData<Resource<List<ListEntity>>> = filmRepository.getAllTvShow()
}