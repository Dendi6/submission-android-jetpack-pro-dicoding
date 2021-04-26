package com.dendi.filmscatalogs.ui.detail

import androidx.lifecycle.ViewModel
import com.dendi.filmscatalogs.data.source.local.entity.FilmEntity
import com.dendi.filmscatalogs.utils.DataDummy

class DetailActivityViewModel : ViewModel() {
    private lateinit var movieTitle: String
    private lateinit var tvShowTitle: String

    private fun getListMovie(): ArrayList<FilmEntity> = DataDummy.generateDummyMovies()
    private fun getListTvShow(): ArrayList<FilmEntity> = DataDummy.generateDummyTvShow()

    fun setMovieTitle(movieTitle: String) {
        this.movieTitle = movieTitle
    }

    fun setTvShowTitle(tvShowTitle: String) {
        this.tvShowTitle = tvShowTitle
    }

    fun getDetailMovieByTitle(): FilmEntity {
        lateinit var result: FilmEntity
        val listMovie = getListMovie()
        for (movie in listMovie) {
            if (movie.title == movieTitle) {
                result = movie
                break
            }
        }
        return result
    }

    fun getDetailTvShowByTitle(): FilmEntity {
        lateinit var result: FilmEntity
        val listTvShow = getListTvShow()
        for (tvShow in listTvShow) {
            if (tvShow.title == tvShowTitle) {
                result = tvShow
                break
            }
        }
        return result
    }
}