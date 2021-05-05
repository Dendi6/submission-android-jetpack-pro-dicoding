package com.dendi.filmscatalogs.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListEntity(
    var id: Int? = 0,
    var title: String? = null,
    var name: String? = null,
    var images: String? = null,
    var poster: String? = null,
    var overview: String? = null
) : Parcelable