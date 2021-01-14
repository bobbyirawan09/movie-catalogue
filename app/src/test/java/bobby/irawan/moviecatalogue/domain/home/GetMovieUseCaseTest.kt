package bobby.irawan.moviecatalogue.domain.home

import bobby.irawan.movieapp.helper.BaseTest
import bobby.irawan.movieapp.helper.MockData
import bobby.irawan.movieapp.helper.MockData.movie
import bobby.irawan.movieapp.helper.MockData.movieId
import bobby.irawan.moviecatalogue.data.repository.Repository
import bobby.irawan.moviecatalogue.domain.commons.Result
import bobby.irawan.moviecatalogue.presentation.model.MovieModelView
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Bobby Irawan on 10/11/20.
 */
@ExperimentalCoroutinesApi
class GetMovieUseCaseTest : BaseTest() {

    @MockK
    private lateinit var mockRepository: Repository

    private lateinit var usecase: GetMovieUseCase

    override fun setup() {
        super.setup()
        usecase = GetMovieUseCase(mockRepository)
    }

    @Test
    fun execute() {
        lateinit var expectedResult: List<MovieModelView>
        Result.Success(listOf(movie)).handleResult(successDataBlock = {
            expectedResult = it
        })

        // Given
        coEvery { mockRepository.getPopularMovies(movieId) } returns flowOf(MockData.successResourceMovieResponse)
        lateinit var result: List<MovieModelView>

        // When
        runBlocking {
            usecase.execute(movieId).collect {
                it.handleResult(successDataBlock = {
                    result = it
                })
            }
            coVerify { mockRepository.getPopularMovies(movieId) }

            // Then
            coVerifySequence {
                mockRepository.getPopularMovies(movieId)
            }
            assertEquals(movieId, result.first().id)
            assertEquals(
                expectedResult.first().id,
                result.first().id
            )
        }
    }
}