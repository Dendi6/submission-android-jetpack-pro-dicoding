package com.dendi.filmscatalogs.ui.tvshow

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest{
    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setup(){
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShow(){
        val result = viewModel.getTvShow()
        assertNotNull(result)
        assertEquals(10,result.size)
    }
}