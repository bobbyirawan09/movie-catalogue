package bobby.irawan.moviecatalogue.presentation.model

/**
 * Created by bobbyirawan09 on 13/08/20.
 */
data class MovieModelView(
    var id: Int,
    var title: String,
    var posterUrl: String,
    var backdropUrl: String,
    var releaseDate: String,
    var overview: String,
    var voteAverage: Double,
    var voteCount: Int
)