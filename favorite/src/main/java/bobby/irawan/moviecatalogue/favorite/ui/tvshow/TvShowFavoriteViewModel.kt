package bobby.irawan.moviecatalogue.favorite.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import bobby.irawan.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase
import bobby.irawan.moviecatalogue.favorite.model.FavoriteModelView
import bobby.irawan.moviecatalogue.favorite.utils.DataMapper
import kotlinx.coroutines.flow.map

class TvShowFavoriteViewModel(
    private val movieCatalogueUseCase: MovieCatalogueUseCase
) : ViewModel() {

    var currentSort: Int = 1
        private set

    fun getTvShowFavorites() =
        movieCatalogueUseCase.getFavoriteTvShow(currentSort).map { favorites ->
            favorites.map {
                DataMapper.favoriteDomainToPresentation(it)
            }
        }.asLiveData()

    fun onSortSelected(choice: Int): LiveData<List<FavoriteModelView>> {
        currentSort = choice
        return movieCatalogueUseCase.getFavoriteTvShow(currentSort).map { favorites ->
            favorites.map {
                DataMapper.favoriteDomainToPresentation(it)
            }
        }.asLiveData()
    }

}