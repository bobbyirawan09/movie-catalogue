package bobby.irawan.moviecatalogue.core.utils

import bobby.irawan.moviecatalogue.core.data.common.orZero
import bobby.irawan.moviecatalogue.core.data.local.entity.FavoriteEntity
import bobby.irawan.moviecatalogue.core.data.remote.common.GenreResponse
import bobby.irawan.moviecatalogue.core.data.remote.movie.model.MovieDetailResponse
import bobby.irawan.moviecatalogue.core.data.remote.movie.model.MovieResponse
import bobby.irawan.moviecatalogue.core.data.remote.tvshow.model.SeasonResponse
import bobby.irawan.moviecatalogue.core.data.remote.tvshow.model.TvShowDetailResponse
import bobby.irawan.moviecatalogue.core.data.remote.tvshow.model.TvShowResponse
import bobby.irawan.moviecatalogue.core.domain.commons.asBackdropUrl
import bobby.irawan.moviecatalogue.core.domain.commons.asPosterUrl
import bobby.irawan.moviecatalogue.core.domain.commons.asReleaseYear
import bobby.irawan.moviecatalogue.core.domain.commons.asShowDate
import bobby.irawan.moviecatalogue.core.domain.model.*

/**
 * Created by Bobby Irawan on 03/01/21.
 */
object DataMapper {
    fun favoriteEntityToDomain(favoriteEntity: FavoriteEntity?) = FavoriteDomainModel(
        favoriteEntity?.id.orZero(),
        favoriteEntity?.title.orEmpty(),
        favoriteEntity?.releaseDate.orEmpty(),
        favoriteEntity?.posterUrl.orEmpty(),
        favoriteEntity?.backdropUrl.orEmpty(),
        favoriteEntity?.overview.orEmpty(),
        favoriteEntity?.voteCount.orZero(),
        favoriteEntity?.voteAverage.orZero(),
        favoriteEntity?.type.orEmpty()
    )

    fun movieDetailResponseToDomain(response: MovieDetailResponse?): MovieDetailDomainModel =
        MovieDetailDomainModel(
            id = response?.id.orZero(),
            title = response?.title.orEmpty(),
            releaseDate = response?.releaseDate.asReleaseYear(),
            overview = response?.overview.orEmpty(),
            posterUrl = response?.posterPath.asPosterUrl(),
            backdropUrl = response?.backdropPath.asBackdropUrl(),
            voteAverage = response?.voteAverage.orZero(),
            voteCount = response?.voteCount.orZero(),
            type = "movie",
            genres = response?.genres?.map {
                genreResponseToDomain(it)
            }.orEmpty(),
            imdbId = response?.imdbId.orEmpty(),
            duration = response?.runtime.orZero(),
            originalTitle = response?.originalTitle.orEmpty()
        )

    fun tvShowDetailResponseToDomain(response: TvShowDetailResponse?): TvShowDetailDomainModel =
        TvShowDetailDomainModel(
            id = response?.id.orZero(),
            title = response?.name.orEmpty(),
            releaseDate = response?.firstAirDate.asReleaseYear(),
            overview = response?.overview.orEmpty(),
            posterUrl = response?.posterPath.asPosterUrl(),
            backdropUrl = response?.backdropPath.asBackdropUrl(),
            voteAverage = response?.voteAverage.orZero(),
            voteCount = response?.voteCount.orZero(),
            type = "tv_show",
            seasons = response?.seasons?.map {
                seasonResponseToDomain(it)
            }.orEmpty(),
            lastAirDate = response?.lastAirDate.asShowDate(),
            numberOfEpisodes = response?.numberOfEpisodes.orZero(),
            numberOfSeasons = response?.numberOfSeasons.orZero(),
            genres = response?.genres?.map {
                genreResponseToDomain(it)
            }.orEmpty()
        )

    fun seasonResponseToDomain(response: SeasonResponse): SeasonDomainModel =
        SeasonDomainModel(
            response.id,
            response.name,
            response.episodeCount,
            response.airDate,
            response.overview,
            response.posterPath.asPosterUrl(),
            response.seasonNumber
        )

    fun genreResponseToDomain(response: GenreResponse): GenreDomainModel = GenreDomainModel(
        response.id,
        response.name
    )

    fun movieResponseToDomain(response: MovieResponse): MovieDomainModel =
        MovieDomainModel(
            response.id,
            response.title,
            response.posterPath.asPosterUrl(),
            response.backdropPath.asBackdropUrl(),
            response.releaseDate.asShowDate(),
            response.overview,
            response.voteAverage.orZero(),
            response.voteCount.orZero()
        )

    fun tvShowResponseToDomain(response: TvShowResponse): TvShowDomainModel =
        TvShowDomainModel(
            response.id,
            response.name,
            response.posterPath.asPosterUrl(),
            response.backdropPath.asBackdropUrl(),
            response.firstAirDate.asShowDate(),
            response.overview,
            response.voteAverage,
            response.voteCount
        )

    fun movieResponseToSearchDomain(response: MovieResponse): SearchDomainModel =
        SearchDomainModel(
            response.id,
            response.title,
            response.posterPath.asPosterUrl(),
            response.backdropPath.asBackdropUrl(),
            response.releaseDate.asShowDate(),
            response.voteAverage.orZero(),
            response.voteCount.orZero()
        )

    fun tvShowResponseToSearchDomain(response: TvShowResponse): SearchDomainModel =
        SearchDomainModel(
            response.id,
            response.name,
            response.posterPath.asPosterUrl(),
            response.backdropPath.asBackdropUrl(),
            response.firstAirDate.asShowDate(),
            response.voteAverage,
            response.voteCount
        )

}