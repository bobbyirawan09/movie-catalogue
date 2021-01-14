package bobby.irawan.moviecatalogue.presentation.model

/**
 * Created by Bobby Irawan on 28/10/20.
 */
class TvShowDetailModelView : ItemDetailModelView() {
    var seasons: List<SeasonModelView> = listOf()
    var numberOfEpisodes: Int = 0
    var genres: List<GenreModelView> = listOf()
}