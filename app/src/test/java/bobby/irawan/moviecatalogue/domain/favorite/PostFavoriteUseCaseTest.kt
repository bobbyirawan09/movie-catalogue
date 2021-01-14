package bobby.irawan.moviecatalogue.domain.favorite

import bobby.irawan.movieapp.helper.BaseTest
import bobby.irawan.movieapp.helper.MockData.favoriteMovie
import bobby.irawan.moviecatalogue.data.repository.Repository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.just
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

/**
 * Created by Bobby Irawan on 03/12/20.
 */
@ExperimentalCoroutinesApi
class PostFavoriteUseCaseTest : BaseTest() {

    @MockK
    private lateinit var mockRepository: Repository

    private lateinit var usecase: PostFavoriteUseCase

    override fun setup() {
        super.setup()
        usecase = PostFavoriteUseCase(mockRepository)
    }

    @Test
    fun execute() = runBlockingTest {
        coEvery { mockRepository.setFavorite(favoriteMovie) } just Runs

        usecase.execute(favoriteMovie)

        coVerify {
            usecase.execute(favoriteMovie)
            mockRepository.setFavorite(favoriteMovie)
        }
    }
}