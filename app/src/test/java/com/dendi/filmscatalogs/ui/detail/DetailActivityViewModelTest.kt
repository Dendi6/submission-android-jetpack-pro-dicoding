package com.dendi.filmscatalogs.ui.detail

import com.dendi.filmscatalogs.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailActivityViewModelTest{
    private lateinit var viewModel: DetailActivityViewModel
    private val dummyDataMovies = DataDummy.generateDummyMovies()[5]
    private val dummyDataTvShow = DataDummy.generateDummyTvShow()[5]
    private val moviesTitle = dummyDataMovies.title
    private val tvShowTitle = dummyDataTvShow.title

    @Before
    fun setup(){
        viewModel = DetailActivityViewModel()
    }

    @Test
    fun moviesDetail(){
        viewModel.setMovieTitle(moviesTitle)
        val result = viewModel.getDetailMovieByTitle()
        assertNotNull(result)
        assertEquals(dummyDataMovies.images, result.images)
        assertEquals(dummyDataMovies.title, result.title)
        assertEquals(dummyDataMovies.year, result.year)
        assertEquals(dummyDataMovies.dateRelease, result.dateRelease)
        assertEquals(dummyDataMovies.duration, result.duration)
        assertEquals(dummyDataMovies.genre, result.genre)
        assertEquals(dummyDataMovies.overview, result.overview)
    }

    @Test
    fun tvShowDetail(){
        viewModel.setTvShowTitle(tvShowTitle)
        val result = viewModel.getDetailTvShowByTitle()
        assertNotNull(result)
        assertEquals(dummyDataTvShow.images, result.images)
        assertEquals(dummyDataTvShow.title, result.title)
        assertEquals(dummyDataTvShow.year, result.year)
        assertEquals(dummyDataTvShow.dateRelease, result.dateRelease)
        assertEquals(dummyDataTvShow.duration, result.duration)
        assertEquals(dummyDataTvShow.genre, result.genre)
        assertEquals(dummyDataTvShow.overview, result.overview)
    }
}