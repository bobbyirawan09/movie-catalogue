package bobby.irawan.moviecatalogue.domain.favorite

import androidx.lifecycle.MutableLiveData
import bobby.irawan.movieapp.helper.BaseTest
import bobby.irawan.movieapp.helper.MockData.favoriteMovieId
import bobby.irawan.movieapp.helper.MockData.favoriteMovieIdDesc
import bobby.irawan.movieapp.helper.MockData.favoriteTvShowId
import bobby.irawan.movieapp.helper.MockData.favoriteTvShowIdDesc
import bobby.irawan.movieapp.helper.MockData.listFavoriteMovie
import bobby.irawan.movieapp.helper.MockData.listFavoriteMovieDesc
import bobby.irawan.movieapp.helper.MockData.listFavoriteTvShow
import bobby.irawan.movieapp.helper.MockData.listFavoriteTvShowDesc
import bobby.irawan.moviecatalogue.data.repository.Repository
import bobby.irawan.moviecatalogue.helper.PagedListUtil
import bobby.irawan.moviecatalogue.utils.Constants.ITEM_MOVIE
import bobby.irawan.moviecatalogue.utils.Constants.ITEM_TV_SHOW
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Bobby Irawan on 03/12/20.
 */
@ExperimentalCoroutinesApi
class GetAllFavoriteUseCaseTest : BaseTest() {

    @MockK
    private lateinit var mockRepository: Repository

    private lateinit var usecase: GetAllFavoriteUseCase

    override fun setup() {
        super.setup()
        usecase = GetAllFavoriteUseCase(mockRepository)
    }

    @Test
    fun execute() {
        val pagedList = PagedListUtil.mockPagedList(listFavoriteMovie + listFavoriteTvShow)
        val pagedListLiveData = MutableLiveData(pagedList)
        every { mockRepository.getFavorites() } returns pagedListLiveData
        every { usecase.execute() } returns pagedListLiveData
        every { pagedListLiveData.value.get(2).id } returns favoriteTvShowId

        val result = usecase.execute()

        verify {
            mockRepository.getFavorites()
        }
        assertEquals(favoriteMovieId, result.value.get(0).id)
        assertEquals(favoriteTvShowId, result.value.get(2).id)
    }

    @Test
    fun executeFavoriteMovie() {
        val pagedList = PagedListUtil.mockPagedList(listFavoriteMovie)
        every { mockRepository.getFavoriteMovie(0) } returns MutableLiveData(pagedList)
        every { usecase.execute(ITEM_MOVIE, 0) } returns MutableLiveData(pagedList)

        val result = usecase.execute(ITEM_MOVIE, 0)

        verify {
            mockRepository.getFavoriteMovie(0)
        }
        assertEquals(favoriteMovieId, result.value.get(0).id)
    }

    @Test
    fun executeFavoriteTvShow() {
        val pagedList = PagedListUtil.mockPagedList(listFavoriteTvShow)
        every { mockRepository.getFavoriteTvShow(0) } returns MutableLiveData(pagedList)
        every { usecase.execute(ITEM_TV_SHOW, 0) } returns MutableLiveData(pagedList)

        val result = usecase.execute(ITEM_TV_SHOW, 0)

        verify {
            mockRepository.getFavoriteTvShow(0)
        }
        assertEquals(favoriteTvShowId, result.value.get(0).id)
    }

    @Test
    fun executeFavoriteMovieDescendingOrder() {
        val pagedList = PagedListUtil.mockPagedList(listFavoriteMovieDesc)
        every { mockRepository.getFavoriteMovie(2) } returns MutableLiveData(pagedList)
        every { usecase.execute(ITEM_MOVIE, 2) } returns MutableLiveData(pagedList)

        val result = usecase.execute(ITEM_MOVIE, 2)

        verify {
            mockRepository.getFavoriteMovie(2)
        }
        assertEquals(favoriteMovieIdDesc, result.value.get(0).id)
    }

    @Test
    fun executeFavoriteTvShowDescendingOrder() {
        val pagedList = PagedListUtil.mockPagedList(listFavoriteTvShowDesc)
        every { mockRepository.getFavoriteTvShow(2) } returns MutableLiveData(pagedList)
        every { usecase.execute(ITEM_TV_SHOW, 2) } returns MutableLiveData(pagedList)

        val result = usecase.execute(ITEM_TV_SHOW, 2)

        verify {
            mockRepository.getFavoriteTvShow(2)
        }
        assertEquals(favoriteTvShowIdDesc, result.value.get(0).id)
    }
}