package com.dendi.filmscatalogs.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dendi.filmscatalogs.data.FilmRepository
import com.dendi.filmscatalogs.data.source.local.entity.DetailEntity
import com.dendi.filmscatalogs.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailActivityViewModelTest {
    private lateinit var viewModel: DetailActivityViewModel
    private val dummyDataMovies = DataDummy.generateDataDummyMovies()[0]
    private val dummyDataTvShow = DataDummy.generateDataDummyTvShow()[0]
    private val dummyMoviesId = dummyDataMovies.id
    private val dummyTvId = dummyDataTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<DetailEntity>

    @Before
    fun setup() {
        viewModel = DetailActivityViewModel(filmRepository)
    }

    @Test
    fun loadMoviesDetail() {
        val movies = MutableLiveData<DetailEntity>()
        movies.value = dummyDataMovies

        viewModel.setSelectedFilm(dummyMoviesId)

        `when`(filmRepository.getDetailMovies(dummyMoviesId)).thenReturn(movies)
        val result = viewModel.getMovies().value as DetailEntity

        verify(filmRepository).getDetailMovies(dummyMoviesId)
        assertNotNull(result)

        assertEquals(dummyDataMovies.id, result.id)
        assertEquals(dummyDataMovies.title, result.title)
        assertEquals(dummyDataMovies.name, result.name)
        assertEquals(dummyDataMovies.poster, result.poster)
        assertEquals(dummyDataMovies.overview, result.overview)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyDataMovies)
    }

    @Test
    fun loadTvShowDetail() {
        val tvShow = MutableLiveData<DetailEntity>()
        tvShow.value = dummyDataTvShow

        viewModel.setSelectedFilm(dummyTvId)
        `when`(filmRepository.getDetailTvShow(dummyTvId)).thenReturn(tvShow)
        val result = viewModel.getTvShow().value as DetailEntity
        verify(filmRepository).getDetailTvShow(dummyTvId)

        assertNotNull(result)
        assertEquals(dummyDataTvShow.id, result.id)
        assertEquals(dummyDataTvShow.title, result.title)
        assertEquals(dummyDataTvShow.name, result.name)
        assertEquals(dummyDataTvShow.poster, result.poster)
        assertEquals(dummyDataTvShow.overview, result.overview)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dummyDataTvShow)
    }
}