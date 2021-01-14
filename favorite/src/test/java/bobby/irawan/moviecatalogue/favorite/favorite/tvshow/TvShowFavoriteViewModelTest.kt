package bobby.irawan.moviecatalogue.favorite.favorite.tvshow

import bobby.irawan.movieapp.helper.BaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Bobby Irawan on 01/12/20.
 */
@Suppress("UNCHECKED_CAST")
@ExperimentalCoroutinesApi
class TvShowFavoriteViewModelTest : BaseTest() {

//    @MockK(relaxed = true)
//    private lateinit var mockUseCase: GetAllFavoriteUseCase
//
//    @MockK(relaxed = true)
//    private lateinit var viewModel: com.irawan.dicoding.favorite.tvshow.TvShowFavoriteViewModel
//
//    override fun setup() {
//        super.setup()
//        viewModel = com.irawan.dicoding.favorite.tvshow.TvShowFavoriteViewModel(mockUseCase)
//    }
//
//    @Test
//    fun getTvShowFavorites() {
//        val pagedList = PagedListUtil.mockPagedList(listFavoriteTvShow)
//        val resultId = 1416
//
//        every { mockUseCase.execute(ITEM_TV_SHOW, 0) } returns MutableLiveData(pagedList)
//        every { viewModel.getTvShowFavorites() } returns MutableLiveData(pagedList)
//
//        val favoriteEntities = viewModel.getTvShowFavorites().value
//        verify {
//            mockUseCase.execute(ITEM_TV_SHOW, 0)
//        }
//
//        assertNotNull(favoriteEntities)
//        assertEquals(resultId, pagedList.get(0).id.orZero())
//    }
//
//    @Test
//    fun onSortSelected() {
//        val pagedList = PagedListUtil.mockPagedList(listFavoriteTvShowDesc)
//        val resultId = 71712
//
//        every { mockUseCase.execute(ITEM_TV_SHOW, 2) } returns MutableLiveData(pagedList)
//        every { viewModel.onSortSelected(2) } returns MutableLiveData(pagedList)
//
//        val favoriteEntities = viewModel.onSortSelected(2).value
//        verify {
//            mockUseCase.execute(ITEM_TV_SHOW, 2)
//        }
//
//        assertNotNull(favoriteEntities)
//        assertEquals(resultId, pagedList.get(0).id.orZero())
//    }
}