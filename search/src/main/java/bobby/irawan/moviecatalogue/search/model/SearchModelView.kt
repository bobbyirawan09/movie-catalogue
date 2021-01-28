package bobby.irawan.moviecatalogue.search.model

/**
 * Created by bobbyirawan09 on 13/08/20.
 */
data class SearchModelView(
    var id: Int,
    var title: String,
    var posterUrl: String,
    var backdropUrl: String,
    var releaseDate: String,
    var voteAverage: Double,
    var voteCount: Int
)