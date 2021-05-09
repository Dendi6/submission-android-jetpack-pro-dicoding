package com.dendi.filmscatalogs.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dendi.filmscatalogs.data.FilmRepository
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.utils.DataDummy
import com.dendi.filmscatalogs.vo.Resource
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<ListEntity>>>

    @Before
    fun setup() {
        viewModel = HomeViewModel(filmRepository)
    }

    @Test
    fun getMovies(){
        val dummyMovies = Resource.success(DataDummy.generateDummyMovies())
        val movies = MutableLiveData<Resource<List<ListEntity>>>()
        movies.value = dummyMovies

        `when`(filmRepository.getAllMovies()).thenReturn(movies)
        val moviesEntities = viewModel.getMovies().value?.data
        verify(filmRepository).getAllMovies()

        assertNotNull(moviesEntities)
        assertEquals(1, moviesEntities?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getTvShow(){
        val dummyTvShow = Resource.success(DataDummy.generateDummyTvShow())
        val tvShow = MutableLiveData<Resource<List<ListEntity>>>()
        tvShow.value = dummyTvShow

        `when`(filmRepository.getAllTvShow()).thenReturn(tvShow)
        val tvEntities = viewModel.getTvShow().value?.data
        verify(filmRepository).getAllTvShow()

        assertNotNull(tvEntities)
        assertEquals(1, tvEntities?.size)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}