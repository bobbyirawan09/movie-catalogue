package bobby.irawan.moviecatalogue.presentation.home.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bobby.irawan.moviecatalogue.core.data.common.SimpleResult
import bobby.irawan.moviecatalogue.core.domain.model.MovieDomainModel
import bobby.irawan.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase
import bobby.irawan.moviecatalogue.presentation.model.MovieModelView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieViewModel(private val movieCatalogueUseCase: MovieCatalogueUseCase) : ViewModel() {

    private var currentPage = 1

    val movies: MutableList<MovieModelView> = mutableListOf()

    private val _movieItems = MutableLiveData<SimpleResult<List<MovieDomainModel>>>()
    val movieItems = _movieItems as LiveData<SimpleResult<List<MovieDomainModel>>>

    private val _loadingNextPage = MutableLiveData<Boolean>()
    fun loading() = _loadingNextPage as LiveData<Boolean>

    fun loadMovieInitial() {
        viewModelScope.launch {
            movieCatalogueUseCase.getPopularMovies(currentPage).collect {
                _movieItems.postValue(it)
            }
        }
    }

    fun loadMovieNextPage() {
        currentPage++
        viewModelScope.launch {
            _loadingNextPage.postValue(true)
            movieCatalogueUseCase.getPopularMovies(currentPage).collect {
                _movieItems.postValue(it)
            }
            _loadingNextPage.postValue(false)
        }
    }

    fun retryConnection() {
        if (movies.isNotEmpty()) {
            currentPage--
            loadMovieNextPage()
        } else {
            loadMovieInitial()
        }
    }
}