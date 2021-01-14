package bobby.irawan.moviecatalogue.presentation.detail.tvshow

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import bobby.irawan.moviecatalogue.core.data.common.SimpleResult
import bobby.irawan.moviecatalogue.core.domain.model.TvShowDetailDomainModel
import bobby.irawan.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase
import bobby.irawan.moviecatalogue.presentation.common.BaseDetailViewModel
import bobby.irawan.moviecatalogue.presentation.detail.tvshow.TvShowDetailActivity.Companion.EXTRA_TV_SHOW_ID
import bobby.irawan.moviecatalogue.presentation.model.GenreModelView
import bobby.irawan.moviecatalogue.utils.orZero
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Bobby Irawan on 31/10/20.
 */
class TvShowDetailViewModel(
    private val movieCatalogueUseCase: MovieCatalogueUseCase
) : BaseDetailViewModel(movieCatalogueUseCase) {

    var tvShowId: Int = 0
        private set

    private val _tvShowDetail =
        MutableLiveData<SimpleResult<TvShowDetailDomainModel>>()
    val tvShowDetail =
        _tvShowDetail as LiveData<SimpleResult<TvShowDetailDomainModel>>

    private val _tvShowGenre = MutableLiveData<String>()
    val tvShowGenre = _tvShowGenre as LiveData<String>

    fun getFromBundle(intent: Intent?) {
        tvShowId = intent?.getIntExtra(EXTRA_TV_SHOW_ID, 0).orZero()
    }

    fun getTvShowDetail() {
        viewModelScope.launch {
            movieCatalogueUseCase.getDetailTvShow(tvShowId).collect {
                _tvShowDetail.postValue(it)
            }
        }
    }

    fun generateTvShowGenre(genres: List<GenreModelView>) {
        var tvShowGenre = ""
        genres.forEach {
            tvShowGenre = "$tvShowGenre${it.name}, "
        }
        if (tvShowGenre.length > 2) {
            tvShowGenre = tvShowGenre.substring(0, tvShowGenre.length - 2)
            _tvShowGenre.postValue(tvShowGenre)
        }
    }

}