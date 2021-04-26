package com.dendi.filmscatalogs.utils

import com.dendi.filmscatalogs.R
import com.dendi.filmscatalogs.data.source.local.entity.FilmEntity

object DataDummy {
    fun generateDummyMovies(): ArrayList<FilmEntity> {
        val movies = ArrayList<FilmEntity>()

        movies.add(
            FilmEntity(
                R.drawable.poster_overlord,
                "Overlord",
                2018,
                "1h 50m",
                "11/09/2018",
                "Horror, War, Science Fiction",
                "France, June 1944. On the eve of D-Day, some American paratroopers fall behind enemy lines after their aircraft crashes while on a mission to destroy a radio tower in a small village near the beaches of Normandy. After reaching their target, the surviving paratroopers realise that, in addition to fighting the Nazi troops that patrol the village, they also must fight against something else."
            )
        )
        movies.add(
            FilmEntity(
                R.drawable.poster_master_z,
                "Master Z: Ip Man Legacy",
                2018,
                "1h 47m",
                "12/26/2018",
                "Action",
                "Following his defeat by Master Ip, Cheung Tin Chi tries to make a life with his young son in Hong Kong, waiting tables at a bar that caters to expats. But it's not long before the mix of foreigners, money, and triad leaders draw him once again to the fight."
            )
        )
        movies.add(
            FilmEntity(
                R.drawable.poster_infinity_war,
                "Avengers: Infinity War",
                2018,
                "2h 29m",
                "04/27/2018",
                "Adventure, Action, Science Fiction",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain."
            )
        )
        movies.add(
            FilmEntity(
                R.drawable.poster_how_to_train,
                "How to Train Your Dragon",
                2010,
                "1h 38m",
                "03/26/2010",
                "Fantasy, Adventure, Animation, Family",
                "As the son of a Viking leader on the cusp of manhood, shy Hiccup Horrendous Haddock III faces a rite of passage: he must kill a dragon to prove his warrior mettle. But after downing a feared dragon, he realizes that he no longer wants to destroy it, and instead befriends the beast – which he names Toothless – much to the chagrin of his warrior father"
            )
        )
        movies.add(
            FilmEntity(
                R.drawable.poster_glass,
                "Glass",
                2019,
                "2h 9m",
                "01/18/2019",
                "01/18/2019",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men."
            )
        )
        movies.add(
            FilmEntity(
                R.drawable.poster_creed,
                "Creed II",
                2018,
                "2h 10m",
                "11/21/2018",
                "Drama",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life."
            )
        )
        movies.add(
            FilmEntity(
                R.drawable.poster_alita,
                "Alita: Battle Angel",
                2019,
                "2h 2m",
                "02/14/2019",
                "Action, Science Fiction, Adventure",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past."
            )
        )
        movies.add(
            FilmEntity(
                R.drawable.poster_aquaman,
                "Aquaman",
                2018,
                "2h 23m",
                "12/21/2018",
                "Action, Adventure, Fantasy",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne."
            )
        )
        movies.add(
            FilmEntity(
                R.drawable.poster_bohemian,
                "Bohemian Rhapsody",
                2018,
                "2h 15m",
                "11/02/2018",
                "Music, Drama, History",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess."
            )
        )
        movies.add(
            FilmEntity(
                R.drawable.poster_cold_persuit,
                "Cold Pursuit",
                2019,
                "1h 59m",
                "02/08/2019",
                "Action, Crime, Thriller",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution."
            )
        )

        return movies
    }

    fun generateDummyTvShow(): ArrayList<FilmEntity> {
        val tvShow = ArrayList<FilmEntity>()

        tvShow.add(
            FilmEntity(
                R.drawable.poster_arrow,
                "Arrow",
                2012,
                "42m",
                "October 10, 2012",
                "Crime, Drama, Mystery, Action & Adventure",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow."
            )
        )
        tvShow.add(
            FilmEntity(
                R.drawable.poster_doom_patrol,
                "Doom Patrol",
                2019,
                "49m",
                "February 15, 2019",
                "Sci-Fi & Fantasy, Comedy, Drama",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find."
            )
        )
        tvShow.add(
            FilmEntity(
                R.drawable.poster_god,
                "God Friended Me",
                2018,
                "42m",
                "September 30, 2018",
                "Drama, Family, Mystery",
                "A self-proclaimed \"pesky atheist\" is encouraged to help strangers by someone claiming to be God who friends him on Facebook."
            )
        )
        tvShow.add(
            FilmEntity(
                R.drawable.poster_gotham,
                "Gotham",
                2014,
                "43m",
                "September 22, 2014",
                "Drama, Crime, Sci-Fi & Fantasy",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?"
            )
        )
        tvShow.add(
            FilmEntity(
                R.drawable.poster_hanna,
                "Hanna",
                2019,
                "50m",
                "March 28, 2019",
                "Action & Adventure, Drama",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film."
            )
        )
        tvShow.add(
            FilmEntity(
                R.drawable.poster_iron_fist,
                "Marvel's Iron Fist",
                2017,
                "55m",
                "March 17, 2017",
                "Action & Adventure, Drama, Sci-Fi & Fantasy",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny."

            )
        )
        tvShow.add(
            FilmEntity(
                R.drawable.poster_naruto_shipudden,
                "Naruto Shippūden",
                2007,
                "25m",
                "February 15, 2007",
                "Animation, Action & Adventure, Sci-Fi & Fantasy",
                "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki."

            )
        )
        tvShow.add(
            FilmEntity(
                R.drawable.poster_ncis,
                "NCIS",
                2003,
                "45m",
                "September 23, 2003",
                "Crime, Action & Adventure, Drama",
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position."

            )
        )
        tvShow.add(
            FilmEntity(
                R.drawable.poster_riverdale,
                "Riverdale",
                2017,
                "45m",
                "January 26, 2017",
                "Mystery, Drama, Crime",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade."
            )
        )
        tvShow.add(
            FilmEntity(
                R.drawable.poster_shameless,
                "Shameless",
                2011,
                "57m",
                "January 9, 2011",
                "Drama, Comedy",
                "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be... perhaps better off. When Frank's not at the bar spending what little money they have, he's passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are."
            )
        )
        return tvShow
    }
}