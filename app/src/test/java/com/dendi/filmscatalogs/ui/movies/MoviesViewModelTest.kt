package com.dendi.filmscatalogs.ui.movies

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MoviesViewModelTest{
    private lateinit var viewModel:MoviesViewModel

    @Before
    fun setup(){
        viewModel = MoviesViewModel()
    }

    @Test
    fun getMovies(){
        val resultMovies = viewModel.getMovies()
        assertNotNull(resultMovies)
        assertEquals(10, resultMovies.size)
    }
}