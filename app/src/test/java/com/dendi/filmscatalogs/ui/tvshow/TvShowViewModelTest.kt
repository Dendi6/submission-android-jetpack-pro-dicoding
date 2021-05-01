package com.dendi.filmscatalogs.ui.tvshow

import com.dendi.filmscatalogs.data.FilmRepository
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Before
    fun setup() {
        viewModel = TvShowViewModel(filmRepository)
    }

    @Test
    fun getTvShow() {
        Mockito.`when`(filmRepository.getAllTvShow()).thenReturn(DataDummy.generateDummyTvShow() as ArrayList<ListEntity> )
        val result = viewModel.getTvShow()
        Mockito.verify(filmRepository).getAllTvShow()
        assertNotNull(result)
        assertEquals(2, result.size)
    }
}