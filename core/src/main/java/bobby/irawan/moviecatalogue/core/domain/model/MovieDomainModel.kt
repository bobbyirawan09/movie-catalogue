package bobby.irawan.moviecatalogue.core.domain.model

/**
 * Created by Bobby Irawan on 01/01/21.
 */
data class MovieDomainModel(
    var id: Int,
    var title: String,
    var asPosterUrl: String,
    var asBackdropUrl: String,
    var asShowDate: String,
    var overview: String,
    var voteAverage: Double,
    var voteCount: Int
)