package com.dendi.filmscatalogs.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.data.source.remote.RemoteDataSource
import com.dendi.filmscatalogs.data.source.remote.response.ListResponse

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

    override fun getAllMovies(): LiveData<List<ListEntity>> {
        val moviesResults = MutableLiveData<List<ListEntity>>()

        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadAllMoviesCallback {
            override fun onAllMoviesReceived(moviesResponses: List<ListResponse>) {
                val listItem = ArrayList<ListEntity>()
                for (response in moviesResponses) {
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

                moviesResults.postValue(listItem)
            }
        })

        return moviesResults
    }

    override fun getAllTvShow(): LiveData<List<ListEntity>> {
        val tvShowResults = MutableLiveData<List<ListEntity>>()

        remoteDataSource.getAllTvShow(object : RemoteDataSource.LoadAllTvCallback {
            override fun onAllTvShowReceived(tvShowReponse: List<ListResponse>) {
                val listItem = ArrayList<ListEntity>()

                for (response in tvShowReponse) {
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
                tvShowResults.postValue(listItem)
            }
        })

        return tvShowResults
    }

    override fun getDetailMovies(id: Int): LiveData<ListEntity> {
        val detailMovieResult = MutableLiveData<ListEntity>()

        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadAllMoviesCallback {
            override fun onAllMoviesReceived(moviesResponses: List<ListResponse>) {
                lateinit var movies: ListEntity
                for (response in moviesResponses) {
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
                detailMovieResult.postValue(movies)
            }

        })

        return detailMovieResult
    }

    override fun getDetailTvShow(id: Int): LiveData<ListEntity> {
        val detailTvShowResponse = MutableLiveData<ListEntity>()

        remoteDataSource.getAllTvShow(object : RemoteDataSource.LoadAllTvCallback {
            override fun onAllTvShowReceived(tvShowReponse: List<ListResponse>) {
                lateinit var tvShow: ListEntity
                for (response in tvShowReponse) {
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
                detailTvShowResponse.postValue(tvShow)
            }

        })

        return detailTvShowResponse
    }
}