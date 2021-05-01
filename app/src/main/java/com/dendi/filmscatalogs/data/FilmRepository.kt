package com.dendi.filmscatalogs.data

import com.dendi.filmscatalogs.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.data.source.remote.RemoteDataSource
import java.util.*

class FilmRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    FilmDataSource {
    companion object {
        @Volatile
        private var instance: FilmRepository? = null

        fun getInstance(remoteData: RemoteDataSource): FilmRepository =
            instance ?: synchronized(this) {
                instance ?: FilmRepository(remoteData).apply { instance = this }
            }
    }

    override fun getAllMovies(): List<ListEntity> {
        val movieResponses = remoteDataSource.getAllMovies()
        val listItem = ArrayList<ListEntity>()

        for (response in movieResponses) {
            val item = ListEntity(
                response.id,
                response.title,
                null,
                response.posterPath,
                response.backdropPath,
                response.overview
            )

            listItem.add(item)
        }
        return listItem
    }

    override fun getAllTvShow(): List<ListEntity> {
        val tvShowResponses = remoteDataSource.getAllTvShow()
        val listItem = ArrayList<ListEntity>()

        for (response in tvShowResponses) {
            val item = ListEntity(
                response.id,
                null,
                response.name,
                response.posterPath,
                response.backdropPath,
                response.overview
            )

            listItem.add(item)
        }
        return listItem
    }

    override fun getDetailMovies(id: Int): ListEntity {
        val movieResponse = remoteDataSource.getAllMovies()
        lateinit var movies: ListEntity
        for (response in movieResponse) {
            if (response.id == id) {
                movies = ListEntity(
                    response.id,
                    response.title,
                    null,
                    response.posterPath,
                    response.backdropPath,
                    response.overview
                )
            }
        }
        return movies
    }

    override fun getDetailTvShow(id:Int):ListEntity{
        val tvShowResponses = remoteDataSource.getAllTvShow()
        lateinit var tvShow: ListEntity
        for (response in tvShowResponses) {
            if (response.id == id) {
                tvShow = ListEntity(
                    response.id,
                    null,
                    response.name,
                    response.posterPath,
                    response.backdropPath,
                    response.overview
                )
            }
        }
        return tvShow
    }
}