package com.dendi.filmscatalogs.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesEntity(
    var id: Int = 0,
    var title: String? = null,
    var images: String? = null,
    var releaseDate: String? = null,
    var overview: String? = null
) : Parcelable