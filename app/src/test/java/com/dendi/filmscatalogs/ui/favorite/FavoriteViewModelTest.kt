package com.dendi.filmscatalogs.ui.favorite

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
class FavoriteViewModelTest {
    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<List<ListEntity>>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(filmRepository)
    }

    @Test
    fun getFavorited() {
        val dummyCourses = DataDummy.generateDummyMovies()
        val courses = MutableLiveData<List<ListEntity>>()
        courses.value = dummyCourses

        `when`(filmRepository.getFavorited()).thenReturn(courses)
        val courseEntities = viewModel.getFavorite().value
        verify(filmRepository).getFavorited()

        assertNotNull(courseEntities)
        assertEquals(1, courseEntities?.size)

        viewModel.getFavorite().observeForever(observer)
        verify(observer).onChanged(dummyCourses)
    }
}