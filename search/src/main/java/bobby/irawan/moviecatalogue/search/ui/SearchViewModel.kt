package bobby.irawan.moviecatalogue.search.ui

import androidx.lifecycle.*
import bobby.irawan.moviecatalogue.core.data.common.SimpleResult
import bobby.irawan.moviecatalogue.core.domain.model.MovieDomainModel
import bobby.irawan.moviecatalogue.core.domain.model.TvShowDomainModel
import bobby.irawan.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase
import bobby.irawan.moviecatalogue.core.utils.Constants.ITEM_MOVIE
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class SearchViewModel(private val movieCatalogueUseCase: MovieCatalogueUseCase) : ViewModel() {

    private var page: Int = 1

    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

    private var searchType = ITEM_MOVIE

    private val _movie =
        MutableLiveData<SimpleResult<List<MovieDomainModel>>>()
    val movie =
        _movie as LiveData<SimpleResult<List<MovieDomainModel>>>

    private val _tvShow =
        MutableLiveData<SimpleResult<List<TvShowDomainModel>>>()
    val tvShow =
        _tvShow as LiveData<SimpleResult<List<TvShowDomainModel>>>

    val searchResult = queryChannel.asFlow()
        .debounce(300)
        .distinctUntilChanged()
        .mapLatest {query ->
            if (query.isEmpty()) {
                page = 1
            }
            movieCatalogueUseCase.getMovieSearchResult(query, page)
            page++
        }
        .asLiveData()

    fun searchKeyword(keyword: String) {
        viewModelScope.launch {
            if (searchType.equals(ITEM_MOVIE, true)) {
                movieCatalogueUseCase.getMovieSearchResult(keyword, page).collect {result ->
                    _movie.value = result
                }
            } else {
                movieCatalogueUseCase.getTvShowSearchResult(keyword, page).collect {result ->
                    _tvShow.value = result
                }
            }
        }
    }

    fun setSearchType(searchType: String) {
        this.searchType = searchType
    }
}