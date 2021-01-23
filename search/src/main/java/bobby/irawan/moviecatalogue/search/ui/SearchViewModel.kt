package bobby.irawan.moviecatalogue.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import bobby.irawan.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapLatest

@FlowPreview
@ExperimentalCoroutinesApi
class SearchViewModel(private val movieCatalogueUseCase: MovieCatalogueUseCase) : ViewModel() {

    private var page: Int = 1

    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

    val searchResult = queryChannel.asFlow()
        .debounce(300)
        .distinctUntilChanged()
        .mapLatest {query ->
            if (query.isEmpty()) {
                page = 1
            }
            movieCatalogueUseCase.getMovieSearchResult(query, page)
        }
        .asLiveData()
}