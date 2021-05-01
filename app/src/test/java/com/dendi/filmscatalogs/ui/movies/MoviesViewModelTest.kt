package com.dendi.filmscatalogs.ui.movies

import com.dendi.filmscatalogs.data.FilmRepository
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {
    private lateinit var viewModel: MoviesViewModel

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Before
    fun setup() {
        viewModel = MoviesViewModel(filmRepository)
    }

    @Test
    fun getMovies() {
        `when`(filmRepository.getAllMovies()).thenReturn(DataDummy.generateDummyMovies() as ArrayList<ListEntity>)
        val moviesData = viewModel.getMovies()
        verify(filmRepository).getAllMovies()
        assertNotNull(moviesData)
        assertEquals(2, moviesData.size)
    }
}