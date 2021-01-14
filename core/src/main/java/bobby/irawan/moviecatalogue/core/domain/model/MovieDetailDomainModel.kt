package bobby.irawan.moviecatalogue.core.domain.model

/**
 * Created by Bobby Irawan on 01/01/21.
 */
data class MovieDetailDomainModel(
    var id: Int,
    var title: String,
    var releaseDate: String,
    var overview: String,
    var posterUrl: String,
    var backdropUrl: String,
    var voteAverage: Double,
    var voteCount: Int,
    var type: String,
    var imdbId: String,
    var duration: Int,
    var genres: List<GenreDomainModel>,
    var originalTitle: String
)