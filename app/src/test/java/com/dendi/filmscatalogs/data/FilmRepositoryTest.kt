package com.dendi.filmscatalogs.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dendi.filmscatalogs.data.source.remote.RemoteDataSource
import com.dendi.filmscatalogs.utils.DataDummy
import com.dendi.filmscatalogs.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.mock

class FilmRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val filmRepository = FakeFilmRepository(remote)

    private val moviesResponse = DataDummy.generateRemoteDummyMovies()
    private val moviesId = moviesResponse[0].id
    private val tvShowResponse = DataDummy.generateRemoteDummyTvShow()
    private val tvShowId = tvShowResponse[0].id
    private val dataMovie = DataDummy.generateRemoteDataDummyMovies()[0]
    private val dataTvShow = DataDummy.generateRemoteDataDummyTvShow()[0]

    @Test
    fun getAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadAllMoviesCallback).onAllMoviesReceived(
                moviesResponse
            )
            null
        }.`when`(remote).getAllMovies(any())
        val courseEntities = LiveDataTestUtil.getValue(filmRepository.getAllMovies())
        verify(remote).getAllMovies(any())
        assertNotNull(courseEntities)
        assertEquals(moviesResponse.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getAllTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadAllTvCallback).onAllTvShowReceived(
                tvShowResponse
            )
            null
        }.`when`(remote).getAllTvShow(any())
        val courseEntities = LiveDataTestUtil.getValue(filmRepository.getAllTvShow())
        verify(remote).getAllTvShow(any())
        assertNotNull(courseEntities)
        assertEquals(tvShowResponse.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getDetailMovies() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadDetailMovieCallback).onDetailMovieReceived(
                dataMovie
            )
            null
        }.`when`(remote).getDetailMovies(eq(moviesId), any())

        val moviesEntities = LiveDataTestUtil.getValue(filmRepository.getDetailMovies(moviesId))
        verify(remote).getDetailMovies(eq(moviesId), any())


        assertNotNull(moviesEntities)
        assertEquals(dataMovie.id, moviesEntities.id)
    }

    @Test
    fun getDetailTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadDetailTvShowCallback).onDetailTvShowReceived(
                dataTvShow
            )
            null
        }.`when`(remote).getDetailTvShow(eq(tvShowId), any())

        val tvEntities = LiveDataTestUtil.getValue(filmRepository.getDetailTvShow(tvShowId))
        verify(remote).getDetailTvShow(eq(tvShowId), any())

        assertNotNull(tvEntities)
        assertEquals(dataTvShow.id, tvEntities.id)
    }
}