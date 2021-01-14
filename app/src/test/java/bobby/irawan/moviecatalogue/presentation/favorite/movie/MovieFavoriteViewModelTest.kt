package bobby.irawan.moviecatalogue.presentation.favorite.movie

import androidx.lifecycle.MutableLiveData
import bobby.irawan.movieapp.helper.BaseTest
import bobby.irawan.movieapp.helper.MockData
import bobby.irawan.moviecatalogue.domain.favorite.GetAllFavoriteUseCase
import bobby.irawan.moviecatalogue.helper.PagedListUtil
import bobby.irawan.moviecatalogue.utils.Constants.ITEM_MOVIE
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import junit.framework.Assert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

/**
 * Created by Bobby Irawan on 01/12/20.
 */
@ExperimentalCoroutinesApi
class MovieFavoriteViewModelTest : BaseTest() {

    @MockK(relaxed = true)
    private lateinit var mockUseCase: GetAllFavoriteUseCase

    @MockK(relaxed = true)
    private lateinit var viewModel: com.irawan.dicoding.favorite.movie.MovieFavoriteViewModel

    override fun setup() {
        super.setup()
        viewModel = com.irawan.dicoding.favorite.movie.MovieFavoriteViewModel(mockUseCase)
    }

    @Test
    fun getMovieFavorites() {
        val pagedList = PagedListUtil.mockPagedList(MockData.listFavoriteMovie)
        val resultId = 528085

        every { mockUseCase.execute(ITEM_MOVIE, 0) } returns MutableLiveData(pagedList)
        every { viewModel.getMovieFavorites() } returns MutableLiveData(pagedList)

        val favoriteEntities = viewModel.getMovieFavorites().value
        verify {
            mockUseCase.execute(ITEM_MOVIE, 0)
        }

        Assert.assertNotNull(favoriteEntities)
        Assert.assertEquals(resultId, pagedList.get(0).id.orZero())
    }

    @Test
    fun onSortSelected() {
        val pagedList = PagedListUtil.mockPagedList(MockData.listFavoriteMovieDesc)
        val resultId = 724989

        every { mockUseCase.execute(ITEM_MOVIE, 2) } returns MutableLiveData(pagedList)
        every { viewModel.onSortSelected(2) } returns MutableLiveData(pagedList)

        val favoriteEntities = viewModel.onSortSelected(2).value
        verify {
            mockUseCase.execute(ITEM_MOVIE, 2)
        }

        Assert.assertNotNull(favoriteEntities)
        Assert.assertEquals(resultId, pagedList.get(0).id.orZero())
    }
}