package com.dendi.filmscatalogs.ui.movies

import androidx.lifecycle.ViewModel
import com.dendi.filmscatalogs.data.FilmEntity
import com.dendi.filmscatalogs.utils.DataDummy

class MoviesViewModel: ViewModel() {
    fun getMovies( ):List<FilmEntity> = DataDummy.generateDummyMovies()
}