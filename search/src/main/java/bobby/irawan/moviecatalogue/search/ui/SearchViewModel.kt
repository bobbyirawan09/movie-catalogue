package bobby.irawan.moviecatalogue.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bobby.irawan.moviecatalogue.core.data.common.SimpleResult
import bobby.irawan.moviecatalogue.core.domain.model.SearchDomainModel
import bobby.irawan.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase
import bobby.irawan.moviecatalogue.core.utils.Constants.ITEM_MOVIE
import bobby.irawan.moviecatalogue.search.model.SearchModelView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class SearchViewModel(private val movieCatalogueUseCase: MovieCatalogueUseCase) : ViewModel() {

    private var page: Int = 1
    private var keyword = ""

    val searchItem: MutableList<SearchModelView> = mutableListOf()

    var searchType = ITEM_MOVIE
        private set

    private val _searchResult =
        MutableLiveData<SimpleResult<List<SearchDomainModel>>>()
    val searchResult =
        _searchResult as LiveData<SimpleResult<List<SearchDomainModel>>>

    private val _loadingNextPage = MutableLiveData<Boolean>()
    fun loading() = _loadingNextPage as LiveData<Boolean>

    fun searchKeyword(keyword: String) {
        page = 1
        this.keyword = keyword
        viewModelScope.launch {
            movieCatalogueUseCase.getSearchResult(searchType, keyword, page).collect { result ->
                _searchResult.value = result
            }
        }
    }

    fun setSearchType(searchType: String) {
        this.searchType = searchType
    }

    fun searchKeywordNextPage() {
        page++
        viewModelScope.launch {
            _loadingNextPage.postValue(true)
            movieCatalogueUseCase.getSearchResult(searchType, keyword, page).collect {
                _searchResult.postValue(it)
            }
            _loadingNextPage.postValue(false)
        }
    }

    fun retryConnection() {
        if (searchItem.isNotEmpty()) {
            page--
            searchKeywordNextPage()
        } else {
            searchKeyword(keyword)
        }
    }
}