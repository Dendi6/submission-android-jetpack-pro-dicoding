package com.dendi.filmscatalogs.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dendi.filmscatalogs.data.source.local.LocalDataSource
import com.dendi.filmscatalogs.data.source.local.entity.DetailEntity
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.data.source.remote.ApiResponse
import com.dendi.filmscatalogs.data.source.remote.RemoteDataSource
import com.dendi.filmscatalogs.data.source.remote.response.DetailResponse
import com.dendi.filmscatalogs.data.source.remote.response.ListResponse
import com.dendi.filmscatalogs.utils.AppExecutors
import com.dendi.filmscatalogs.vo.Resource

class FilmRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    FilmDataSource {
    companion object {
        @Volatile
        private var instance: FilmRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): FilmRepository =
            instance ?: synchronized(this) {
                instance ?: FilmRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }

    override fun getAllMovies(): LiveData<Resource<List<ListEntity>>> {
        return object : NetworkBoundResource<List<ListEntity>, List<ListResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<ListEntity>> =
                localDataSource.getMovies()

            override fun shouldFetch(data: List<ListEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ListResponse>>> =
                remoteDataSource.getAllMovies()

            public override fun saveCallResult(data: List<ListResponse>) {
                val listItem = ArrayList<ListEntity>()
                for (response in data) {
                    val item = response.id?.let {
                        ListEntity(
                            it,
                            response.title,
                            null,
                            response.posterPath,
                            response.backdropPath,
                            response.overview,
                            false,
                            "movies"
                        )
                    }

                    item?.let { listItem.add(it) }
                }

                localDataSource.insertFilm(listItem)
            }
        }.asLiveData()
    }

    override fun getAllTvShow(): LiveData<Resource<List<ListEntity>>> {
        return object : NetworkBoundResource<List<ListEntity>, List<ListResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<ListEntity>> =
                localDataSource.getTvShow()

            override fun shouldFetch(data: List<ListEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ListResponse>>> =
                remoteDataSource.getAllTvShow()

            public override fun saveCallResult(data: List<ListResponse>) {
                val listItem = ArrayList<ListEntity>()
                for (response in data) {
                    val item = response.id?.let {
                        ListEntity(
                            it,
                            null,
                            response.name,
                            response.posterPath,
                            response.backdropPath,
                            response.overview,
                            false,
                            "tv"
                        )
                    }

                    item?.let { listItem.add(it) }
                }

                localDataSource.insertFilm(listItem)
            }
        }.asLiveData()
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