@file:Suppress("UNCHECKED_CAST")

package bobby.irawan.movieapp.helper

import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import bobby.irawan.moviecatalogue.core.data.remote.common.ErrorResponse
import bobby.irawan.moviecatalogue.core.data.remote.tvshow.model.TvShowResponse
import bobby.irawan.moviecatalogue.data.common.ErrorType
import bobby.irawan.moviecatalogue.data.common.Resource
import com.irawan.dicoding.core.data.common.SimpleBaseResponse
import bobby.irawan.moviecatalogue.data.local.entity.FavoriteEntity
import bobby.irawan.moviecatalogue.data.remote.common.BaseResponse
import bobby.irawan.moviecatalogue.data.remote.common.GenreResponse
import bobby.irawan.moviecatalogue.data.remote.common.ProductionCompanyResponse
import bobby.irawan.moviecatalogue.data.remote.movie.model.MovieDetailResponse
import bobby.irawan.moviecatalogue.data.remote.movie.model.MovieResponse
import bobby.irawan.moviecatalogue.data.remote.movie.model.ProductionCountryResponse
import bobby.irawan.moviecatalogue.data.remote.movie.model.SpokenLanguageResponse
import bobby.irawan.moviecatalogue.data.remote.tvshow.model.*
import bobby.irawan.moviecatalogue.presentation.model.*
import io.mockk.mockkClass
import kotlinx.coroutines.flow.flowOf
import retrofit2.Response

/**
 * Created by bobbyirawan09 on 15/08/20.
 */
object MockData {

    const val initialPage = 1
    const val nextPage = 2
    const val tvShowId = 31917
    const val movieId = 297761

    val movie = MovieModelView(
        id = 297761,
        title = "Suicide Squad",
        posterUrl = "/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg",
        backdropUrl = "/ndlQ2Cuc3cjTL7lTynw6I4boP4S.jpg",
        releaseDate = "03 August 2016",
        overview = "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.",
        voteAverage = 5.91,
        voteCount = 1466
    )
    val movieList = listOf(movie)
    val successResultMovie = Result.Success(movieList)
    val successMovieResult = flowOf(successResultMovie)
    val errorResponse = ErrorResponse(message = "Can't get data", type = ErrorType.OTHER)
    val errorResultMovie = Result.Failure(errorResponse)
    val errorMovieResult = flowOf(errorResultMovie)

    val tvShow = TvShowModelView(
        id = 31917,
        title = "Pretty Little Liars",
        posterUrl = "/vC324sdfcS313vh9QXwijLIHPJp.jpg",
        backdropUrl = "/rQGBjWNveVeF8f2PGRtS85w9o9r.jpg",
        firstAirDate = "06 August 2010",
        overview = "Based on the Pretty Little Liars series of young adult novels by Sara Shepard, the series follows the lives of four girls — Spencer, Hanna, Aria, and Emily — whose clique falls apart after the disappearance of their queen bee, Alison. One year later, they begin receiving messages from someone using the name \\\"A\\\" who threatens to expose their secrets — including long-hidden ones they thought only Alison knew.",
        voteAverage = 5.04,
        voteCount = 133
    )
    val tvShowList = listOf(tvShow)
    val successResultTvShow = Result.Success(tvShowList)
    val successTvShowResult = flowOf(successResultTvShow)

    val genre = GenreModelView(
        id = 18,
        name = "Drama"
    )

    val movieDetail = MovieDetailModelView().apply {
        title = "Suicide Squad"
        posterUrl = "/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg"
        backdropUrl = "/ndlQ2Cuc3cjTL7lTynw6I4boP4S.jpg"
        releaseDate = "03 August 2016"
        overview =
            "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences."
        voteAverage = 5.91
        voteCount = 1466
        imdbId = "tt0060921"
        genres = listOf(genre)
        duration = 126
        originalTitle = "Suicide Squad"
    }
    val successResultMovieDetail = Result.Success(movieDetail)
    val successMovieDetailResult = flowOf(successResultMovieDetail)

    //Movie Response
    val movieResponse = MovieResponse(
        title = "Suicide Squad",
        posterPath = "/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg",
        backdropPath = "/ndlQ2Cuc3cjTL7lTynw6I4boP4S.jpg",
        releaseDate = "03 August 2016",
        overview = "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.",
        voteAverage = 5.91,
        voteCount = 1466,
        originalTitle = "Suicide Squad",
        adult = false,
        genreIds = listOf(18),
        id = 297761,
        originalLanguage = "en",
        popularity = 55.937,
        video = false
    )
    val listMovieResponse = listOf(movieResponse)
    val simpleMovieBaseResponse = BaseResponse(
        results = listOf(movieResponse),
        total_pages = 10,
        total_results = 1
    )
    val successMovieResponse: _root_ide_package_.com.irawan.dicoding.core.data.common.SimpleBaseResponse<List<MovieResponse>> =
        Response.success(simpleMovieBaseResponse)

    val successResourceMovieResponse: Resource<List<MovieResponse>> =
        Resource.success(listOf(movieResponse))

    //Movie Detail Response
    val genreResponse = GenreResponse(
        id = 18,
        name = "Drama"
    )
    val productionCompanyResponse = ProductionCompanyResponse(
        id = 9993,
        logoPath = "/2Tc1P3Ac8M479naPp1kYT3izLS5.png",
        name = "DC Entertainment",
        originCountry = "US"
    )
    val productionCoountryResponse = ProductionCountryResponse(
        iso_3166_1 = "US",
        name = "United States of America"
    )
    val spokenLanguageResponse = SpokenLanguageResponse(
        iso_639_1 = "en",
        name = "English"
    )
    val movieDetailResponse = MovieDetailResponse(
        title = "Suicide Squad",
        posterPath = "/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg",
        backdropPath = "/ndlQ2Cuc3cjTL7lTynw6I4boP4S.jpg",
        releaseDate = "03 August 2016",
        overview = "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.",
        voteAverage = 5.91,
        voteCount = 1466,
        imdbId = "tt0060921",
        genres = listOf(genreResponse),
        runtime = 126,
        originalTitle = "Suicide Squad",
        adult = false,
        belongsToCollection = Any(),
        budget = 175000000,
        homepage = "http://www.suicidesquad.com/",
        id = 297761,
        originalLanguage = "en",
        popularity = 55.937,
        productionCompanies = listOf(productionCompanyResponse),
        productionCountries = listOf(productionCoountryResponse),
        revenue = 746846894,
        spokenLanguages = listOf(spokenLanguageResponse),
        status = "Released",
        tagline = "Worst Heroes Ever",
        video = false
    )
    val imdbId = "tt0060921"
    val successMovieDetailResponse: Response<MovieDetailResponse> =
        Response.success(movieDetailResponse)
    val successResourceMovieDetailResponse: Resource<MovieDetailResponse> =
        Resource.success(movieDetailResponse)

    //Tv Show
    val season = SeasonModelView(
        id = 66519,
        title = "Season 6",
        episodeCount = 20,
        airDate = "02 June 2015",
        posterUrl = "/qdvS6s5QRIx9lrgJLS29606vFmY.jpg",
        seasonNumber = 6
    )
    val tvShowDetail = TvShowDetailModelView().apply {
        id = 31917
        title = "Pretty Little Liars"
        seasons = listOf(season)
        genres = listOf(genre)
        posterUrl = "/vC324sdfcS313vh9QXwijLIHPJp.jpg"
        backdropUrl = "/rQGBjWNveVeF8f2PGRtS85w9o9r.jpg"
        releaseDate = "06 August 2010"
        lastAirDate = "27 June 2017"
        overview =
            "Based on the Pretty Little Liars series of young adult novels by Sara Shepard, the series follows the lives of four girls — Spencer, Hanna, Aria, and Emily — whose clique falls apart after the disappearance of their queen bee, Alison. One year later, they begin receiving messages from someone using the name \\\"A\\\" who threatens to expose their secrets — including long-hidden ones they thought only Alison knew."
        voteAverage = 5.04
        voteCount = 133
        numberOfEpisodes = 161
        numberOfSeasons = 7
    }
    val successResultTvShowDetail = Result.Success(tvShowDetail)
    val successTvShowDetailResult = flowOf(successResultTvShowDetail)

    //Tv Show Response
    val tvShowResponse = TvShowResponse(
        id = 31917,
        name = "Pretty Little Liars",
        genreIds = listOf(18),
        posterPath = "/vC324sdfcS313vh9QXwijLIHPJp.jpg",
        backdropPath = "/rQGBjWNveVeF8f2PGRtS85w9o9r.jpg",
        firstAirDate = "06 August 2010",
        overview = "Based on the Pretty Little Liars series of young adult novels by Sara Shepard, the series follows the lives of four girls — Spencer, Hanna, Aria, and Emily — whose clique falls apart after the disappearance of their queen bee, Alison. One year later, they begin receiving messages from someone using the name \\\"A\\\" who threatens to expose their secrets — including long-hidden ones they thought only Alison knew.",
        voteAverage = 5.04,
        voteCount = 133,
        originalName = "Pretty Little Liars",
        originCountry = listOf("US"),
        originalLanguage = "en",
        popularity = 135.537
    )
    val listTvShowResponse = listOf(tvShowResponse)
    val simpleTvShowBaseResponse = BaseResponse(
        results = listOf(tvShowResponse),
        total_pages = 10,
        total_results = 1
    )
    val successTvShowResponse: _root_ide_package_.com.irawan.dicoding.core.data.common.SimpleBaseResponse<List<TvShowResponse>> =
        Response.success(simpleTvShowBaseResponse)
    val successResourceTvShowResponse: Resource<List<TvShowResponse>> =
        Resource.success(listOf(tvShowResponse))

    //Tv Show Detail Response
    val seasonResponse = SeasonResponse(
        airDate = "02 June 2015",
        episodeCount = 20,
        id = 66519,
        name = "Season 6",
        overview = "The girls may have gotten out of the Dollhouse but what happened to them during their time of captivity has lasting effects. With worried loved ones watching over them, the PLLs are home and trying to heal, with not much success. Even with suspected tormentor Andrew in custody, Aria, Emily, Hanna and Spencer fear they are far from safe. Meanwhile, Alison must deal with her past indiscretions and her notoriety around Rosewood.",
        posterPath = "/qdvS6s5QRIx9lrgJLS29606vFmY.jpg",
        seasonNumber = 6
    )
    val createdByResponse = CreatedByResponse(
        id = 57062,
        creditId = "5258a144760ee34661655204",
        name = "I. Marlene King",
        gender = 1,
        profilePath = "/kODXeOzxJC8SINh7xeOlmzlHPNS.jpg"
    )
    val lastEpisodeToAirResponse = LastEpisodeToAirResponse(
        airDate = "2017-06-27",
        episodeNumber = 20,
        id = 1328760,
        name = "Till Death Do Us Part",
        overview = "All is revealed as the ultimate endgame comes to light.",
        productionCode = "",
        seasonNumber = 7,
        showId = 31917,
        stillPath = "/xxqryU7IK4uFEyrKWbuGbHALbHC.jpg",
        voteAverage = 6.5,
        voteCount = 2
    )
    val networkResponse = NetworkResponse(
        name = "ABC Family",
        id = 75,
        logoPath = "/p57JGkSUBdXbOtqkEKeTnfHn7kd.png",
        originCountry = "US"
    )
    val tvShowDetailResponse = TvShowDetailResponse(
        id = 31917,
        name = "Pretty Little Liars",
        originalName = "Pretty Little Liars",
        seasons = listOf(seasonResponse),
        genres = listOf(genreResponse),
        posterPath = "/vC324sdfcS313vh9QXwijLIHPJp.jpg",
        backdropPath = "/rQGBjWNveVeF8f2PGRtS85w9o9r.jpg",
        firstAirDate = "06 August 2010",
        lastAirDate = "27 June 2017",
        overview = "Based on the Pretty Little Liars series of young adult novels by Sara Shepard, the series follows the lives of four girls — Spencer, Hanna, Aria, and Emily — whose clique falls apart after the disappearance of their queen bee, Alison. One year later, they begin receiving messages from someone using the name \\\"A\\\" who threatens to expose their secrets — including long-hidden ones they thought only Alison knew.",
        voteAverage = 5.04,
        voteCount = 133,
        numberOfEpisodes = 161,
        numberOfSeasons = 7,
        createdBy = listOf(createdByResponse),
        episodeRunTime = listOf(41),
        homepage = "http://freeform.go.com/shows/pretty-little-liars",
        inProduction = false,
        languages = listOf("en"),
        lastRpisodeToAir = lastEpisodeToAirResponse,
        networks = listOf(networkResponse),
        nextEpisodeToAir = Any(),
        originCountry = listOf("US"),
        originalLanguage = "en",
        popularity = 135.537,
        productionCompanies = listOf(productionCompanyResponse),
        status = "Ended",
        type = "Scripted"
    )
    val successTvShowDetailResponse: Response<TvShowDetailResponse> =
        Response.success(tvShowDetailResponse)
    val successResourceTvShowDetailResponse: Resource<TvShowDetailResponse> =
        Resource.success(tvShowDetailResponse)

    //Favorite
    val favoriteTvShow = FavoriteEntity(
        id = 1416,
        title = "Grey's Anatomy",
        releaseDate = "27 March 2005",
        posterUrl = "https://image.tmdb.org/t/p/w342/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
        backdropUrl = "https://image.tmdb.org/t/p/w780/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
        overview = "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
        voteCount = 4435,
        voteAverage = 8.1,
        type = "tv_show"
    )
    val modelFavoriteTvShow = bobby.irawan.moviecatalogue.favorite.model.FavoriteModelView(
        id = 1416,
        title = "Grey's Anatomy",
        releaseDate = "27 March 2005",
        posterUrl = "https://image.tmdb.org/t/p/w342/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
        overview = "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
        voteCount = 4435,
        voteAverage = 8.1
    )
    val favoriteTvShowId = 1416
    val favoriteTvShowIdDesc = 71712

    val favoriteTvShow2 = FavoriteEntity(
        id = 71712,
        title = "The Good Doctor",
        releaseDate = "25 September 2017",
        posterUrl = "https://image.tmdb.org/t/p/w342/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
        backdropUrl = "https://image.tmdb.org/t/p/w780/iDbIEpCM9nhoayUDTwqFL1iVwzb.jpg",
        overview = "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives?",
        voteCount = 5865,
        voteAverage = 8.6,
        type = "tv_show"
    )

    val listFavoriteTvShow = listOf(favoriteTvShow, favoriteTvShow2)
    val listFavoriteTvShowDesc = listOf(favoriteTvShow2, favoriteTvShow)

    val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(10)
        .setPageSize(10)
        .build()
    val dataSourceFactory = mockkClass(DataSource.Factory::class, relaxed = true) as DataSource.Factory<Int, FavoriteEntity>
    val pagedListFavorite = LivePagedListBuilder(dataSourceFactory, config).build()

    val favoriteMovie = FavoriteEntity(
        id = 528085,
        title = "2067",
        releaseDate = "01 October 2020",
        posterUrl = "https://image.tmdb.org/t/p/w342/7D430eqZj8y3oVkLFfsWXGRcpEG.jpg",
        backdropUrl = "https://image.tmdb.org/t/p/w780/5UkzNSOK561c2QRy2Zr4AkADzLT.jpg",
        overview = "A lowly utility worker is called to the future by a mysterious radio signal, he must leave his dying wife to embark on a journey that will force him to face his deepest fears in an attempt to change the fabric of reality and save humankind from its greatest environmental crisis yet.",
        voteCount = 348,
        voteAverage = 4.7,
        type = "movie"
    )
    val modelFavoriteMovie = bobby.irawan.moviecatalogue.favorite.model.FavoriteModelView(
        id = 528085,
        title = "2067",
        releaseDate = "01 October 2020",
        posterUrl = "https://image.tmdb.org/t/p/w342/7D430eqZj8y3oVkLFfsWXGRcpEG.jpg",
        overview = "A lowly utility worker is called to the future by a mysterious radio signal, he must leave his dying wife to embark on a journey that will force him to face his deepest fears in an attempt to change the fabric of reality and save humankind from its greatest environmental crisis yet.",
        voteCount = 348,
        voteAverage = 4.7
    )

    val favoriteMovieId = 528085
    val favoriteMovieIdDesc = 724989

    val favoriteMovie2 = FavoriteEntity(
        id = 724989,
        title = "Hard Kill",
        releaseDate = "23 October 2020",
        posterUrl = "https://image.tmdb.org/t/p/w342/ugZW8ocsrfgI95pnQ7wrmKDxIe.jpg",
        backdropUrl = "https://image.tmdb.org/t/p/w780/86L8wqGMDbwURPni2t7FQ0nDjsH.jpg",
        overview = "The work of billionaire tech CEO Donovan Chalmers is so valuable that he hires mercenaries to protect it, and a terrorist group kidnaps his daughter just to get it.",
        voteCount = 166,
        voteAverage = 5.0,
        type = "movie"
    )
    val listFavoriteMovie = listOf(favoriteMovie, favoriteMovie2)
    val listFavoriteMovieDesc = listOf(favoriteMovie2, favoriteMovie)

}