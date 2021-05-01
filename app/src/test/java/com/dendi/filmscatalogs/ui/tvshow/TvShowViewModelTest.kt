package com.dendi.filmscatalogs.ui.tvshow

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
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<List<ListEntity>>

    @Before
    fun setup() {
        viewModel = TvShowViewModel(filmRepository)
    }

    @Test
    fun getTvShow() {
        val dummyData = DataDummy.generateDummyTvShow()
        val data = MutableLiveData<List<ListEntity>>()
        data.value = dummyData

        Mockito.`when`(filmRepository.getAllTvShow()).thenReturn(data)
        val result = viewModel.getTvShow().value
        Mockito.verify(filmRepository).getAllTvShow()
        assertNotNull(result)
        assertEquals(2, result?.size)

        viewModel.getTvShow().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyData)
    }
}