package bobby.irawan.moviecatalogue.presentation.detail.movie

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import bobby.irawan.moviecatalogue.core.data.common.SimpleResult
import bobby.irawan.moviecatalogue.core.domain.model.MovieDetailDomainModel
import bobby.irawan.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase
import bobby.irawan.moviecatalogue.presentation.common.BaseDetailViewModel
import bobby.irawan.moviecatalogue.presentation.detail.movie.MovieDetailActivity.Companion.EXTRA_MOVIE_ID
import bobby.irawan.moviecatalogue.presentation.model.GenreModelView
import bobby.irawan.moviecatalogue.presentation.model.MovieDetailModelView
import bobby.irawan.moviecatalogue.utils.orZero
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Bobby Irawan on 25/10/20.
 */
class MovieDetailViewModel(
    private val movieCatalogueUseCase: MovieCatalogueUseCase
) : BaseDetailViewModel(movieCatalogueUseCase) {

    private val _movieDetail =
        MutableLiveData<SimpleResult<MovieDetailDomainModel>>()
    val movieDetail =
        _movieDetail as LiveData<SimpleResult<MovieDetailDomainModel>>

    private val _movieGenre = MutableLiveData<String>()
    val movieGenre = _movieGenre as LiveData<String>

    var movieId: Int = 0
        private set

    fun getFromBundle(intent: Intent?) {
        movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0).orZero()
    }

    fun getMovieDetail() {
        viewModelScope.launch {
            movieCatalogueUseCase.getDetailMovie(movieId).collect {
                _movieDetail.postValue(it)
            }
        }
    }

    fun generateMovieGenre(genres: List<GenreModelView>) {
        var movieGenre = ""
        genres.forEach {
            movieGenre = "$movieGenre${it.name}, "
        }
        if (movieGenre.length > 2) {
            movieGenre = movieGenre.substring(0, movieGenre.length - 2)
            _movieGenre.postValue(movieGenre)
        }
    }
}