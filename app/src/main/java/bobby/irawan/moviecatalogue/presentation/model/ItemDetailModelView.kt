package bobby.irawan.moviecatalogue.presentation.model

/**
 * Created by Bobby Irawan on 21/11/20.
 */
open class ItemDetailModelView {
    var id: Int = 0
    var title: String = ""
    var releaseDate: String = ""
    var overview: String = ""
    var posterUrl: String = ""
    var backdropUrl: String = ""
    var voteAverage: Double = 0.0
    var voteCount: Int = 0
    var type: String = ""
}