package com.dendi.filmscatalogs.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmEntity(
        var images: Int,
        var title: String,
        var year: Int,
        var duration: String,
        var dateRelease : String,
        var genre:String,
        var overview: String
):Parcelable