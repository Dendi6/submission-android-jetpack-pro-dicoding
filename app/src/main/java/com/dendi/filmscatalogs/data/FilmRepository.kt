package com.dendi.filmscatalogs.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dendi.filmscatalogs.data.source.local.entity.DetailEntity
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.data.source.remote.RemoteDataSource
import com.dendi.filmscatalogs.data.source.remote.response.DetailResponse
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
                    val item = response.id?.let {
                        ListEntity(
                            it,
                            response.title,
                            null,
                            response.posterPath,
                            response.backdropPath,
                            response.overview
                        )
                    }

                    item?.let { listItem.add(it) }
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
                    val item = response.id?.let {
                        ListEntity(
                            it,
                            null,
                            response.name,
                            response.posterPath,
                            response.backdropPath,
                            response.overview
                        )
                    }

                    item?.let { listItem.add(it) }
                }
                tvShowResults.postValue(listItem)
            }
        })

        return tvShowResults
    }

    override fun getDetailMovies(id: Int): LiveData<DetailEntity> {
        val detailMovieResult = MutableLiveData<DetailEntity>()

        remoteDataSource.getDetailMovies(id, object : RemoteDataSource.LoadDetailMovieCallback {
            override fun onDetailMovieReceived(movieDetailResponse: DetailResponse) {
                val detailEntity = DetailEntity(
                    movieDetailResponse.id,
                    movieDetailResponse.backdropPath,
                    movieDetailResponse.title,
                    movieDetailResponse.name,
                    movieDetailResponse.overview
                )

                detailMovieResult.postValue(detailEntity)
            }
        })

        return detailMovieResult
    }

    override fun getDetailTvShow(id: Int): LiveData<DetailEntity> {
        val detailTvShowResponse = MutableLiveData<DetailEntity>()

        remoteDataSource.getDetailTvShow(id, object : RemoteDataSource.LoadDetailTvShowCallback {
            override fun onDetailTvShowReceived(tvShowDetailResponse: DetailResponse) {
                val detailEntity = DetailEntity(
                    tvShowDetailResponse.id,
                    tvShowDetailResponse.backdropPath,
                    tvShowDetailResponse.title,
                    tvShowDetailResponse.name,
                    tvShowDetailResponse.overview
                )

                detailTvShowResponse.postValue(detailEntity)
            }

        })

        return detailTvShowResponse
    }
}