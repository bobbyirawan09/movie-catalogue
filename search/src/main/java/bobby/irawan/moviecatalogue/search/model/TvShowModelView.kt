package bobby.irawan.moviecatalogue.search.model

/**
 * Created by Bobby Irawan on 28/10/20.
 */
data class TvShowModelView(
    var id: Int,
    var title: String,
    var posterUrl: String,
    var backdropUrl: String,
    var firstAirDate: String,
    var overview: String,
    var voteAverage: Double,
    var voteCount: Int
)