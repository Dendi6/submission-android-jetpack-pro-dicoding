package com.dendi.filmscatalogs.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailEntity(
    var id: Int,
    var poster: String? = null,
    var title: String? = null,
    var name: String? = null,
    var overview: String? = null
) : Parcelable
