package bobby.irawan.moviecatalogue.favorite.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import bobby.irawan.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase
import bobby.irawan.moviecatalogue.favorite.utils.DataMapper
import kotlinx.coroutines.flow.map

class MovieFavoriteViewModel(
    private val movieCatalogueUseCase: MovieCatalogueUseCase
) : ViewModel() {

    fun getMovieFavorites() = movieCatalogueUseCase.getFavoriteMovie().map { favorites ->
        favorites.map {
            DataMapper.favoriteDomainToPresentation(it)
        }
    }.asLiveData()
}
