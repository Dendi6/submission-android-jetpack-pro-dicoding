package com.dendi.filmscatalogs.utils

import android.content.Context
import com.dendi.filmscatalogs.data.source.remote.response.ListResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {
    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun getMovies(): List<ListResponse> {
        val list = ArrayList<ListResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("Movies.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getInt("id")
                val title = movie.getString("title")
                val images = movie.getString("poster_path")
                val poster = movie.getString("backdrop_path")
                val overview = movie.getString("overview")

                val movieResponse = ListResponse(id, title, null, images, poster, overview)
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun getTvShow(): List<ListResponse> {
        val list = ArrayList<ListResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("TvShow.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val tvShow = listArray.getJSONObject(i)

                val id = tvShow.getInt("id")
                val name = tvShow.getString("name")
                val images = tvShow.getString("poster_path")
                val poster = tvShow.getString("backdrop_path")
                val overview = tvShow.getString("overview")

                val movieResponse = ListResponse(id, null, name, images, poster, overview)
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }
}