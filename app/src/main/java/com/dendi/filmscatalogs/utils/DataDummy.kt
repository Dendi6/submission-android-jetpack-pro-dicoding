package com.dendi.filmscatalogs.utils

import com.dendi.filmscatalogs.data.source.local.entity.DetailEntity
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.data.source.remote.response.DetailResponse
import com.dendi.filmscatalogs.data.source.remote.response.ListResponse

object DataDummy {
    fun generateDummyMovies(): List<ListEntity> {

        val movies = ArrayList<ListEntity>()

        movies.add(
            ListEntity(
                460465,
                "Mortal Kombat",
                null,
                "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe."
            )
        )

        return movies
    }

    fun generateDummyTvShow(): List<ListEntity> {
        val tvShow = ArrayList<ListEntity>()

        tvShow.add(
            ListEntity(
                88396,
                null,
                "The Falcon and the Winter Soldier",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience."
            )
        )

        return tvShow
    }

    fun generateRemoteDummyMovies(): ArrayList<ListResponse> {
        val movies = ArrayList<ListResponse>()

        movies.add(
            ListResponse(
                460465,
                "Mortal Kombat",
                null,
                "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe."
            )
        )

        return movies
    }

    fun generateRemoteDummyTvShow(): ArrayList<ListResponse> {
        val tvShow = ArrayList<ListResponse>()

        tvShow.add(
            ListResponse(
                88396,
                null,
                "The Falcon and the Winter Soldier",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience."
            )
        )

        return tvShow
    }

    fun generateDataDummyMovies(): List<DetailEntity> {
        val movies = ArrayList<DetailEntity>()

        movies.add(
            DetailEntity(
                460465,
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "Mortal Kombat",
                null,
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe."
            )
        )

        return movies
    }

    fun generateDataDummyTvShow(): List<DetailEntity> {
        val tvShow = ArrayList<DetailEntity>()

        tvShow.add(
            DetailEntity(
                88396,
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                null,
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience."
            )
        )

        return tvShow
    }

    fun generateRemoteDataDummyMovies(): ArrayList<DetailResponse> {
        val movies = ArrayList<DetailResponse>()

        movies.add(
            DetailResponse(
                460465,
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "Mortal Kombat",
                null,
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe."
            )
        )

        return movies
    }

    fun generateRemoteDataDummyTvShow(): ArrayList<DetailResponse> {
        val tvShow = ArrayList<DetailResponse>()

        tvShow.add(
            DetailResponse(
                88396,
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                null,
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience."
            )
        )

        return tvShow
    }
}