package com.dendi.filmscatalogs.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmEntity(
    var images: Int? = null,
    var title: String,
    var year: Int? = null,
    var duration: String? = null,
    var dateRelease: String? = null,
    var genre: String? = null,
    var overview: String? = null
) : Parcelable