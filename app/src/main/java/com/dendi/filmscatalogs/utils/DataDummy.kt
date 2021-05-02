package com.dendi.filmscatalogs.utils

import com.dendi.filmscatalogs.data.source.local.entity.DetailEntity
import com.dendi.filmscatalogs.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.data.source.remote.response.DetailResponse
import com.dendi.filmscatalogs.data.source.remote.response.ListResponse

object DataDummy {
    fun generateDummyMovies(): ArrayList<ListEntity> {
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
        movies.add(
            ListEntity(
                399566,
                "Godzilla vs. Kong",
                null,
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages."
            )
        )

        return movies
    }

    fun generateDummyTvShow(): ArrayList<ListEntity> {
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
        tvShow.add(
            ListEntity(
                85720,
                null,
                "Shadow and Bone",
                "/mrVoyDFiDSqfH4mkoRtccOv2vwh.jpg",
                "/8z9qQkx7wA6FXpLV8Tiw9mfsRFK.jpg",
                "In a world cleaved in two by a massive barrier of perpetual darkness, a young soldier uncovers a power that might finally unite her country. But as she struggles to hone her power, dangerous forces plot against her. Thugs, thieves, assassins and saints are at war now, and it will take more than magic to survive."
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
        movies.add(
            ListResponse(
                399566,
                "Godzilla vs. Kong",
                null,
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages."
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
        tvShow.add(
            ListResponse(
                85720,
                null,
                "Shadow and Bone",
                "/mrVoyDFiDSqfH4mkoRtccOv2vwh.jpg",
                "/8z9qQkx7wA6FXpLV8Tiw9mfsRFK.jpg",
                "In a world cleaved in two by a massive barrier of perpetual darkness, a young soldier uncovers a power that might finally unite her country. But as she struggles to hone her power, dangerous forces plot against her. Thugs, thieves, assassins and saints are at war now, and it will take more than magic to survive."
            )
        )

        return tvShow
    }

    fun generateDataDummyMovies(): ArrayList<DetailEntity> {
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
        movies.add(
            DetailEntity(
                399566,
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "Godzilla vs. Kong",
                null,
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages."
            )
        )

        return movies
    }

    fun generateDataDummyTvShow(): ArrayList<DetailEntity> {
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
        tvShow.add(
            DetailEntity(
                85720,
                "/8z9qQkx7wA6FXpLV8Tiw9mfsRFK.jpg",
                null,
                "Shadow and Bone",
                "In a world cleaved in two by a massive barrier of perpetual darkness, a young soldier uncovers a power that might finally unite her country. But as she struggles to hone her power, dangerous forces plot against her. Thugs, thieves, assassins and saints are at war now, and it will take more than magic to survive."
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
        movies.add(
            DetailResponse(
                399566,
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "Godzilla vs. Kong",
                null,
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages."
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
        tvShow.add(
            DetailResponse(
                85720,
                "/8z9qQkx7wA6FXpLV8Tiw9mfsRFK.jpg",
                null,
                "Shadow and Bone",
                "In a world cleaved in two by a massive barrier of perpetual darkness, a young soldier uncovers a power that might finally unite her country. But as she struggles to hone her power, dangerous forces plot against her. Thugs, thieves, assassins and saints are at war now, and it will take more than magic to survive."
            )
        )

        return tvShow
    }
}