package com.dendi.filmscatalogs.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dendi.filmscatalogs.data.FilmRepository
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.vo.Resource

class HomeViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    fun getMovies(): LiveData<Resource<PagedList<ListEntity>>> = filmRepository.getAllMovies()

    fun getTvShow(): LiveData<Resource<PagedList<ListEntity>>> = filmRepository.getAllTvShow()
}