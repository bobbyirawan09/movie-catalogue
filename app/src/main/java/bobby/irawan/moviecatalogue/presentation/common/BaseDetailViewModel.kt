package bobby.irawan.moviecatalogue.presentation.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bobby.irawan.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase
import bobby.irawan.moviecatalogue.presentation.model.ItemDetailModelView
import bobby.irawan.moviecatalogue.utils.DataMapper
import bobby.irawan.moviecatalogue.utils.isZero
import bobby.irawan.moviecatalogue.utils.orZero
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

/**
 * Created by Bobby Irawan on 21/11/20.
 */
abstract class BaseDetailViewModel(private val movieCatalogueUseCase: MovieCatalogueUseCase) :
    ViewModel(), KoinComponent {

    private var _isFavorite = MutableLiveData<Boolean>()
    val isFavorite = _isFavorite as LiveData<Boolean>

    private var itemDetailModelView = ItemDetailModelView()

    fun setItemDetailModelView(itemDetailModelView: ItemDetailModelView) {
        this.itemDetailModelView = itemDetailModelView
    }

    fun onFavoriteClick() {
        if (_isFavorite.value == true) {
            deleteFromFavorite()
        } else {
            addToFavorite()
        }
    }

    fun checkIsFavorite(id: Int) {
        viewModelScope.launch {
            val result = movieCatalogueUseCase.getFavoriteById(id)
            if (!result?.id.orZero().isZero()) {
                _isFavorite.postValue(true)
            }
        }
    }

    private fun addToFavorite() {
        viewModelScope.launch {
            movieCatalogueUseCase.addFavorite(
                DataMapper.itemDetailToFavoriteModel(
                    itemDetailModelView
                )
            )
            _isFavorite.postValue(true)
        }
    }

    private fun deleteFromFavorite() {
        viewModelScope.launch {
            val result = movieCatalogueUseCase.deleteFavorite(
                DataMapper.itemDetailToFavoriteModel(itemDetailModelView)
            )
            if (result > 0) {
                _isFavorite.postValue(false)
            }
        }
    }
}