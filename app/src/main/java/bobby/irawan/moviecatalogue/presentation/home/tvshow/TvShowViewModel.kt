package bobby.irawan.moviecatalogue.presentation.home.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bobby.irawan.moviecatalogue.core.data.common.SimpleResult
import bobby.irawan.moviecatalogue.core.domain.model.TvShowDomainModel
import bobby.irawan.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase
import bobby.irawan.moviecatalogue.presentation.model.TvShowModelView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TvShowViewModel(
    private val movieCatalogueUseCase: MovieCatalogueUseCase
) : ViewModel() {
    private var currentPage = 1

    val tvShows: MutableList<TvShowModelView> = mutableListOf()

    private val _tvShowItems = MutableLiveData<SimpleResult<List<TvShowDomainModel>>>()
    val tvShowItems = _tvShowItems as LiveData<SimpleResult<List<TvShowDomainModel>>>

    private val _loadingNextPage = MutableLiveData<Boolean>()
    fun loading() = _loadingNextPage as LiveData<Boolean>

    fun loadTvShowInitial() {
        viewModelScope.launch {
            movieCatalogueUseCase.getPopularTvShow(currentPage).collect {
                _tvShowItems.postValue(it)
            }
        }
    }

    fun loadTvShowNextPage() {
        currentPage++
        viewModelScope.launch {
            _loadingNextPage.postValue(true)
            movieCatalogueUseCase.getPopularTvShow(currentPage).collect {
                _tvShowItems.postValue(it)
            }
            _loadingNextPage.postValue(false)
        }
    }

    fun retryConnection() {
        if (tvShows.isNotEmpty()) {
            currentPage--
            loadTvShowNextPage()
        } else {
            loadTvShowInitial()
        }
    }
}