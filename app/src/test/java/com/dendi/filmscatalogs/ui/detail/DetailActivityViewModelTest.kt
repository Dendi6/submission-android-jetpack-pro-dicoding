package com.dendi.filmscatalogs.ui.detail

import com.dendi.filmscatalogs.data.FilmRepository
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
class DetailActivityViewModelTest {
    private lateinit var viewModel: DetailActivityViewModel
    private val dummyDataMovies = DataDummy.generateDummyMovies()[0]
    private val dummyDataTvShow = DataDummy.generateDummyTvShow()[0]
    private val dummyMoviesId = dummyDataMovies.id
    private val dummyTvId = dummyDataTvShow.id

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Before
    fun setup() {
        viewModel = DetailActivityViewModel(filmRepository)
    }

    @Test
    fun loadMoviesDetail() {
        viewModel.setSelectedFilm(dummyMoviesId)
        `when`(filmRepository.getDetailMovies(dummyMoviesId)).thenReturn(dummyDataMovies)
        val result = viewModel.getMovies()
        verify(filmRepository).getDetailMovies(dummyMoviesId)
        assertNotNull(result)

        assertEquals(dummyDataMovies.id, result.id)
        assertEquals(dummyDataMovies.title, result.title)
        assertEquals(dummyDataMovies.name, result.name)
        assertEquals(dummyDataMovies.images, result.images)
        assertEquals(dummyDataMovies.poster, result.poster)
        assertEquals(dummyDataMovies.overview, result.overview)
    }

    @Test
    fun loadTvShowDetail() {
        viewModel.setSelectedFilm(dummyTvId)
        `when`(filmRepository.getDetailTvShow(dummyTvId)).thenReturn(dummyDataTvShow)
        val result = viewModel.getTvShow()
        verify(filmRepository).getDetailTvShow(dummyTvId)

        assertNotNull(result)
        assertEquals(dummyDataTvShow.id, result.id)
        assertEquals(dummyDataTvShow.title, result.title)
        assertEquals(dummyDataTvShow.name, result.name)
        assertEquals(dummyDataTvShow.images, result.images)
        assertEquals(dummyDataTvShow.poster, result.poster)
        assertEquals(dummyDataTvShow.overview, result.overview)
    }
}