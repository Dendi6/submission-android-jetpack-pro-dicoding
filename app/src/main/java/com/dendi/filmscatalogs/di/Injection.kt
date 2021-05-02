package com.dendi.filmscatalogs.di

import com.dendi.filmscatalogs.data.FilmRepository
import com.dendi.filmscatalogs.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): FilmRepository {

        val remoteDataSource = RemoteDataSource.getInstance()

        return FilmRepository.getInstance(remoteDataSource)
    }
}