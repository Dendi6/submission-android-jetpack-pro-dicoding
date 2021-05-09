package com.dendi.filmscatalogs.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dendi.filmscatalogs.data.FilmRepository
import com.dendi.filmscatalogs.di.Injection
import com.dendi.filmscatalogs.ui.detail.DetailActivityViewModel
import com.dendi.filmscatalogs.ui.home.HomeViewModel

class ViewModelFactory private constructor(private val mFilmsRepository: FilmRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(mFilmsRepository) as T
            }
            modelClass.isAssignableFrom(DetailActivityViewModel::class.java) -> {
                DetailActivityViewModel(mFilmsRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}