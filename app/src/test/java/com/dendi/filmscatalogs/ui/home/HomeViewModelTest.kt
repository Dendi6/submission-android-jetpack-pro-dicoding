package com.dendi.filmscatalogs.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
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
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<ListEntity>>>

    @Before
    fun setup() {
        viewModel = HomeViewModel(filmRepository)
    }

    @Test
    fun `getMovies should be success`() {
        val courses = PagedTestDataSources.snapshot(DataDummy.generateDummyMovies())
        val expected = MutableLiveData<Resource<PagedList<ListEntity>>>()
        expected.value = Resource.success(courses)

        `when`(filmRepository.getAllMovies()).thenReturn(expected)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getMovies().value

        assertNotNull(actualValue)
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun `getMovies should be success but data is empty`() {
        val movies = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<ListEntity>>>()
        expected.value = Resource.success(movies)

        `when`(filmRepository.getAllMovies()).thenReturn(expected)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualValueDataSize = viewModel.getMovies().value?.data?.size
        assertTrue(
            "size of data should be 0, actual is $actualValueDataSize",
            actualValueDataSize == 0
        )
    }

    @Test
    fun `getMovies should be error`() {
        val expectedMessage = "Something happen dude!"
        val expected = MutableLiveData<Resource<PagedList<ListEntity>>>()
        expected.value = Resource.error(expectedMessage, null)

        `when`(filmRepository.getAllMovies()).thenReturn(expected)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualMessage = viewModel.getMovies().value?.message
        assertEquals(expectedMessage, actualMessage)
    }

    @Test
    fun `getTvShow should be success`() {
        val courses = PagedTestDataSources.snapshot(DataDummy.generateDummyTvShow())
        val expected = MutableLiveData<Resource<PagedList<ListEntity>>>()
        expected.value = Resource.success(courses)

        `when`(filmRepository.getAllTvShow()).thenReturn(expected)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getTvShow().value

        assertNotNull(actualValue)
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun `getTvShow should be success but data is empty`() {
        val tvShow = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<ListEntity>>>()
        expected.value = Resource.success(tvShow)

        `when`(filmRepository.getAllTvShow()).thenReturn(expected)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualValueDataSize = viewModel.getTvShow().value?.data?.size
        assertTrue(
            "size of data should be 0, actual is $actualValueDataSize",
            actualValueDataSize == 0
        )
    }

    @Test
    fun `getTvShow should be error`() {
        val expectedMessage = "Something happen dude!"
        val expected = MutableLiveData<Resource<PagedList<ListEntity>>>()
        expected.value = Resource.error(expectedMessage, null)

        `when`(filmRepository.getAllTvShow()).thenReturn(expected)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualMessage = viewModel.getTvShow().value?.message
        assertEquals(expectedMessage, actualMessage)
    }

    class PagedTestDataSources private constructor(private val items: List<ListEntity>) :
        PositionalDataSource<ListEntity>() {
        companion object {
            fun snapshot(items: List<ListEntity> = listOf()): PagedList<ListEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(
            params: LoadInitialParams,
            callback: LoadInitialCallback<ListEntity>
        ) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<ListEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}