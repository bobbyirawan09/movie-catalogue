package bobby.irawan.moviecatalogue.presentation.model

/**
 * Created by Bobby Irawan on 28/10/20.
 */
class MovieDetailModelView: ItemDetailModelView() {
    var imdbId: String = ""
    var duration: Int = 0
    var genres: List<GenreModelView> = listOf()
    var originalTitle: String = ""
}