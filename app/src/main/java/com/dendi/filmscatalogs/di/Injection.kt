package com.dendi.filmscatalogs.di

import android.content.Context
import com.dendi.filmscatalogs.data.FilmRepository
import com.dendi.filmscatalogs.data.source.local.LocalDataSource
import com.dendi.filmscatalogs.data.source.local.room.FilmDatabase
import com.dendi.filmscatalogs.data.source.remote.RemoteDataSource
import com.dendi.filmscatalogs.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): FilmRepository {

        val database = FilmDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.filmDao())
        val appExecutors = AppExecutors()

        return FilmRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}