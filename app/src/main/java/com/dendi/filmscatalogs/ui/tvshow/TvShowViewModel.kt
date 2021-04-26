package com.dendi.filmscatalogs.ui.tvshow

import androidx.lifecycle.ViewModel
import com.dendi.filmscatalogs.data.source.local.entity.FilmEntity
import com.dendi.filmscatalogs.utils.DataDummy

class TvShowViewModel : ViewModel() {
    fun getTvShow(): List<FilmEntity> = DataDummy.generateDummyTvShow()
}