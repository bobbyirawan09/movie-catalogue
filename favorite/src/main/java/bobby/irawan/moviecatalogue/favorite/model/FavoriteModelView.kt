package bobby.irawan.moviecatalogue.favorite.model

/**
 * Created by Bobby Irawan on 21/11/20.
 */
data class FavoriteModelView(
    var id: Int = 0,
    var title: String = "",
    var releaseDate: String = "",
    var posterUrl: String = "",
    var overview: String = "",
    var voteCount: Int = 0,
    var voteAverage: Double = 0.0
)