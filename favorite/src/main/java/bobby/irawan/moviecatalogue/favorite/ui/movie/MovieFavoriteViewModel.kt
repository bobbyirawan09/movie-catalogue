package bobby.irawan.moviecatalogue.favorite.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import bobby.irawan.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase
import bobby.irawan.moviecatalogue.favorite.model.FavoriteModelView
import bobby.irawan.moviecatalogue.favorite.utils.DataMapper
import kotlinx.coroutines.flow.map

class MovieFavoriteViewModel(
    private val movieCatalogueUseCase: MovieCatalogueUseCase
) : ViewModel() {

    var currentSort: Int = 1
        private set

    fun getMovieFavorites() = movieCatalogueUseCase.getFavoriteMovie(currentSort).map { favorites ->
        favorites.map {
            DataMapper.favoriteDomainToPresentation(it)
        }
    }.asLiveData()

    fun onSortSelected(choice: Int): LiveData<List<FavoriteModelView>> {
        currentSort = choice
        return movieCatalogueUseCase.getFavoriteMovie(choice).map { favorites ->
            favorites.map {
                DataMapper.favoriteDomainToPresentation(it)
            }
        }.asLiveData()
    }
}
