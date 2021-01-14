package bobby.irawan.moviecatalogue.favorite.favorite.movie

import bobby.irawan.movieapp.helper.BaseTest
import bobby.irawan.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase
import bobby.irawan.moviecatalogue.favorite.ui.movie.MovieFavoriteViewModel
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Bobby Irawan on 01/12/20.
 */
@ExperimentalCoroutinesApi
class MovieFavoriteViewModelTest : BaseTest() {

    @MockK(relaxed = true)
    private lateinit var mockUseCase: MovieCatalogueUseCase

    @MockK(relaxed = true)
    private lateinit var viewModel: MovieFavoriteViewModel

    override fun setup() {
        super.setup()
        viewModel = MovieFavoriteViewModel(mockUseCase)
    }

//    @Test
//    fun getMovieFavorites() {
//        val pagedList = PagedListUtil.mockPagedList(MockData.listFavoriteMovie)
//        val resultId = 528085
//
//        every { mockUseCase.getFavoriteMovie(0) } returns MutableLiveData(pagedList)
//        every { viewModel.getMovieFavorites() } returns MutableLiveData(pagedList)
//
//        val favoriteEntities = viewModel.getMovieFavorites().value
//        verify {
//            mockUseCase.getFavoriteMovie(0)
//        }
//
//        Assert.assertNotNull(favoriteEntities)
//        Assert.assertEquals(resultId, pagedList.get(0).id.orZero())
//    }
//
//    @Test
//    fun onSortSelected() {
//        val pagedList = PagedListUtil.mockPagedList(MockData.listFavoriteMovieDesc)
//        val resultId = 724989
//
//        every { mockUseCase.execute(ITEM_MOVIE, 2) } returns MutableLiveData(pagedList)
//        every { viewModel.onSortSelected(2) } returns MutableLiveData(pagedList)
//
//        val favoriteEntities = viewModel.onSortSelected(2).value
//        verify {
//            mockUseCase.execute(ITEM_MOVIE, 2)
//        }
//
//        Assert.assertNotNull(favoriteEntities)
//        Assert.assertEquals(resultId, pagedList.get(0).id.orZero())
//    }
}