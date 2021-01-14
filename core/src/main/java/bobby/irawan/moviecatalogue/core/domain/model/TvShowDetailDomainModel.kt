package bobby.irawan.moviecatalogue.core.domain.model

/**
 * Created by Bobby Irawan on 03/01/21.
 */
data class TvShowDetailDomainModel(
    var id: Int,
    var title: String,
    var releaseDate: String,
    var overview: String,
    var posterUrl: String,
    var backdropUrl: String,
    var voteAverage: Double,
    var voteCount: Int,
    var type: String,
    var seasons: List<SeasonDomainModel>,
    var lastAirDate: String,
    var numberOfEpisodes: Int,
    var numberOfSeasons: Int,
    var genres: List<GenreDomainModel>
)
