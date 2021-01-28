package bobby.irawan.moviecatalogue.core.domain.model

/**
 * Created by Bobby Irawan on 28/01/21.
 */
data class SearchDomainModel(
    var id: Int,
    var title: String,
    var posterUrl: String,
    var backdropUrl: String,
    var releaseDate: String,
    var voteAverage: Double,
    var voteCount: Int
)