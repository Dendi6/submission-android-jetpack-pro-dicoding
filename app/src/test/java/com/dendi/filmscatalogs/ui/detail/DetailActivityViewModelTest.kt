package com.dendi.filmscatalogs.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dendi.filmscatalogs.data.FilmRepository
import com.dendi.filmscatalogs.data.source.local.entity.DetailEntity
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.utils.DataDummy
import com.dendi.filmscatalogs.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
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
    private lateinit var observer: Observer<Resource<DetailEntity>>

    @Before
    fun setup() {
        viewModel = DetailActivityViewModel(filmRepository)
    }


    @Test
    fun loadMoviesDetail() {
        val dataDummy = Resource.success(DataDummy.generateDataDummyMovies()[0])
        val movies = MutableLiveData<Resource<DetailEntity>>()
        movies.value = dataDummy

        dummyMoviesId?.let { viewModel.setSelectedFilm(it) }

        `when`(dummyMoviesId?.let { filmRepository.getDetailMovies(it) }).thenReturn(movies)
        val result = viewModel.getMovies().value?.data

        dummyMoviesId?.let { verify(filmRepository).getDetailMovies(it) }
        assertNotNull(result)

        assertEquals(dummyDataMovies.id, result?.id)
        assertEquals(dummyDataMovies.title, result?.title)
        assertEquals(dummyDataMovies.name, result?.name)
        assertEquals(dummyDataMovies.poster, result?.poster)
        assertEquals(dummyDataMovies.overview, result?.overview)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dataDummy)
    }

    @Test
    fun loadTvShowDetail() {
        val dataDummy = Resource.success(DataDummy.generateDataDummyTvShow()[0])
        val tvShow = MutableLiveData<Resource<DetailEntity>>()
        tvShow.value = dataDummy

        dummyTvId?.let { viewModel.setSelectedFilm(it) }
        `when`(dummyTvId?.let { filmRepository.getDetailTvShow(it) }).thenReturn(tvShow)
        val result = viewModel.getTvShow().value?.data
        dummyTvId?.let { verify(filmRepository).getDetailTvShow(it) }

        assertNotNull(result)
        assertEquals(dummyDataTvShow.id, result?.id)
        assertEquals(dummyDataTvShow.title, result?.title)
        assertEquals(dummyDataTvShow.name, result?.name)
        assertEquals(dummyDataTvShow.poster, result?.poster)
        assertEquals(dummyDataTvShow.overview, result?.overview)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dataDummy)
    }

    @Test
    fun setFavorite() {
        val dummyData = Resource.success(DataDummy.generateDummyMovies()[0].copy(favorited = false))
        val dataItem = MutableLiveData<Resource<ListEntity>>()
        dataItem.value = dummyData

        dummyData.data?.let { doNothing().`when`(filmRepository).setFilmFavorite(it, true) }
        dataItem.value!!.data?.let { viewModel.setFavorite(it, true) }

        verify(filmRepository).setFilmFavorite(dataItem.value?.data as ListEntity, true)
    }

    @Test
    fun delFavorite() {
        val dummyData = Resource.success(DataDummy.generateDummyMovies()[0].copy(favorited = true))
        val dataItem = MutableLiveData<Resource<ListEntity>>()
        dataItem.value = dummyData

        dummyData.data?.let { doNothing().`when`(filmRepository).setFilmFavorite(it, false) }
        dataItem.value!!.data?.let { viewModel.setFavorite(it, false) }

        verify(filmRepository).setFilmFavorite(dataItem.value?.data as ListEntity, false)
    }
}