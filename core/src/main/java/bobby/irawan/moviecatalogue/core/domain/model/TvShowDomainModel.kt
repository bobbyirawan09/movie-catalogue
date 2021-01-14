package bobby.irawan.moviecatalogue.core.domain.model

/**
 * Created by Bobby Irawan on 03/01/21.
 */
data class TvShowDomainModel(
    var id: Int,
    var title: String,
    var posterUrl: String,
    var backdropUrl: String,
    var firstAirDate: String,
    var overview: String,
    var voteAverage: Double,
    var voteCount: Int
)