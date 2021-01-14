package bobby.irawan.moviecatalogue.domain.favorite

import bobby.irawan.movieapp.helper.BaseTest
import bobby.irawan.movieapp.helper.MockData.favoriteMovie
import bobby.irawan.movieapp.helper.MockData.favoriteMovieId
import bobby.irawan.movieapp.helper.MockData.favoriteTvShow
import bobby.irawan.movieapp.helper.MockData.favoriteTvShowId
import bobby.irawan.movieapp.helper.MockData.modelFavoriteMovie
import bobby.irawan.movieapp.helper.MockData.modelFavoriteTvShow
import bobby.irawan.moviecatalogue.data.repository.Repository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Bobby Irawan on 03/12/20.
 */
@ExperimentalCoroutinesApi
class GetFavoriteUseCaseTest : BaseTest() {

    @MockK
    private lateinit var mockRepository: Repository

    private lateinit var usecase: GetFavoriteUseCase

    override fun setup() {
        super.setup()
        usecase = GetFavoriteUseCase(mockRepository)
    }

    @Test
    fun executeFavoriteMovie() = runBlockingTest {
        coEvery { usecase.execute(favoriteMovieId) } returns modelFavoriteMovie
        coEvery { mockRepository.getFavoriteById(favoriteMovieId) } returns favoriteMovie

        val result = usecase.execute(favoriteMovieId)

        coVerify {
            mockRepository.getFavoriteById(favoriteMovieId)
        }
        assertEquals(favoriteMovieId, result.id)
    }

    @Test
    fun executeFavoriteTvShow() = runBlockingTest {
        coEvery { usecase.execute(favoriteTvShowId) } returns modelFavoriteTvShow
        coEvery { mockRepository.getFavoriteById(favoriteTvShowId) } returns favoriteTvShow

        val result = usecase.execute(favoriteTvShowId)

        coVerify {
            mockRepository.getFavoriteById(favoriteTvShowId)
        }
        assertEquals(favoriteTvShowId, result.id)
    }
}