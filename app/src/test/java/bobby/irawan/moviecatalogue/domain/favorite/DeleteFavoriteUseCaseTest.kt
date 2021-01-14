package bobby.irawan.moviecatalogue.domain.favorite

import bobby.irawan.movieapp.helper.BaseTest
import bobby.irawan.movieapp.helper.MockData.favoriteMovie
import bobby.irawan.moviecatalogue.data.repository.Repository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Bobby Irawan on 03/12/20.
 */
@ExperimentalCoroutinesApi
class DeleteFavoriteUseCaseTest : BaseTest() {

    @MockK
    private lateinit var mockRepository: Repository

    private lateinit var usecase: DeleteFavoriteUseCase

    override fun setup() {
        super.setup()
        usecase = DeleteFavoriteUseCase(mockRepository)
    }

    @Test
    fun execute() = runBlocking {

        coEvery { mockRepository.deleteFavorite(favoriteMovie) } returns 1

        val result = usecase.execute(favoriteMovie)

        coVerify { mockRepository.deleteFavorite(favoriteMovie) }
        assertEquals(1, result)
    }

    @Test
    fun executeFailed() = runBlocking {

        coEvery { mockRepository.deleteFavorite(favoriteMovie) } returns 0

        val result = usecase.execute(favoriteMovie)

        coVerify { mockRepository.deleteFavorite(favoriteMovie) }
        assertEquals(0, result)
    }
}