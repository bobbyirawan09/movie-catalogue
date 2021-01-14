package bobby.irawan.moviecatalogue.utils

import bobby.irawan.moviecatalogue.core.domain.model.*
import bobby.irawan.moviecatalogue.presentation.model.*

/**
 * Created by Bobby Irawan on 05/01/21.
 */
object DataMapper {
    fun itemDetailToFavoriteModel(itemDetailModelView: ItemDetailModelView) = FavoriteDomainModel(
        itemDetailModelView.id,
        itemDetailModelView.title,
        itemDetailModelView.releaseDate,
        itemDetailModelView.posterUrl,
        itemDetailModelView.backdropUrl,
        itemDetailModelView.overview,
        itemDetailModelView.voteCount,
        itemDetailModelView.voteAverage,
        itemDetailModelView.type
    )

    fun movieDetailDomainToPresentation(domainModel: MovieDetailDomainModel) =
        MovieDetailModelView().apply {
            id = domainModel.id
            title = domainModel.title
            releaseDate = domainModel.releaseDate
            overview = domainModel.overview
            posterUrl = domainModel.posterUrl
            backdropUrl = domainModel.backdropUrl
            voteAverage = domainModel.voteAverage
            voteCount = domainModel.voteCount
            type = domainModel.type
            imdbId = domainModel.imdbId
            duration = domainModel.duration
            genres = domainModel.genres.map { genreDomainToPresentation(it) }
            originalTitle = domainModel.originalTitle
        }

    fun genreDomainToPresentation(domainModel: GenreDomainModel) = GenreModelView(
        domainModel.id,
        domainModel.name
    )

    fun tvShowDetailDomainToPresentation(domainModel: TvShowDetailDomainModel) =
        TvShowDetailModelView().apply {
            id = domainModel.id
            title = domainModel.title
            releaseDate = domainModel.releaseDate
            overview = domainModel.overview
            posterUrl = domainModel.posterUrl
            backdropUrl = domainModel.backdropUrl
            voteAverage = domainModel.voteAverage
            voteCount = domainModel.voteCount
            type = domainModel.type
            seasons = domainModel.seasons.map { seasonDomainToPresentation(it) }
            numberOfEpisodes = domainModel.numberOfEpisodes
            genres = domainModel.genres.map { genreDomainToPresentation(it) }
        }

    fun seasonDomainToPresentation(domainModel: SeasonDomainModel) = SeasonModelView(
        domainModel.title,
        domainModel.airDate,
        domainModel.posterUrl
    )

    fun movieDomainToPresentation(domainModel: MovieDomainModel) =
        MovieModelView(
            id = domainModel.id,
            title = domainModel.title,
            releaseDate = domainModel.asShowDate,
            overview = domainModel.overview,
            posterUrl = domainModel.asPosterUrl,
            backdropUrl = domainModel.asBackdropUrl,
            voteAverage = domainModel.voteAverage,
            voteCount = domainModel.voteCount
        )

    fun tvShowDomainToPresentation(domainModel: TvShowDomainModel) =
        TvShowModelView(
            id = domainModel.id,
            title = domainModel.title,
            firstAirDate = domainModel.firstAirDate,
            overview = domainModel.overview,
            posterUrl = domainModel.posterUrl,
            backdropUrl = domainModel.backdropUrl,
            voteAverage = domainModel.voteAverage,
            voteCount = domainModel.voteCount
        )
}