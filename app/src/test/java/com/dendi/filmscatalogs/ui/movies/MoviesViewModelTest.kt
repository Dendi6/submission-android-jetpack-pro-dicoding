package com.dendi.filmscatalogs.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dendi.filmscatalogs.data.FilmRepository
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity
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
class MoviesViewModelTest {
    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<List<ListEntity>>

    @Before
    fun setup() {
        viewModel = MoviesViewModel(filmRepository)
    }

    @Test
    fun getMovies() {
        val dummyData = DataDummy.generateDummyMovies()
        val data = MutableLiveData<List<ListEntity>>()
        data.value = dummyData

        `when`(filmRepository.getAllMovies()).thenReturn(data)
        val moviesData = viewModel.getMovies().value
        verify(filmRepository).getAllMovies()
        assertNotNull(moviesData)
        assertEquals(2, moviesData?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyData)
    }
}