package bobby.irawan.moviecatalogue.core.domain.model

/**
 * Created by Bobby Irawan on 17/11/20.
 */
data class FavoriteDomainModel(
    var id: Int,
    var title: String,
    var releaseDate: String,
    var posterUrl: String,
    var backdropUrl: String,
    var overview: String,
    var voteCount: Int,
    var voteAverage: Double,
    var type: String
)