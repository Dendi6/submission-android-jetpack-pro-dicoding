package com.dendi.filmscatalogs.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListResponse (
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("name")
    val name: String?= null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,
):Parcelable