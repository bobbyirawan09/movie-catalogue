package bobby.irawan.moviecatalogue.domain.detail/*
package bobby.irawan.moviecatalogue.domain.detail

import bobby.irawan.movieapp.helper.BaseTest
import bobby.irawan.movieapp.helper.MockData
import bobby.irawan.movieapp.helper.MockData.movieDetail
import bobby.irawan.movieapp.helper.MockData.successResourceMovieDetailResponse
import bobby.irawan.moviecatalogue.data.repository.Repository
import bobby.irawan.moviecatalogue.domain.commons.Result
import bobby.irawan.moviecatalogue.presentation.model.MovieDetailModelView
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

*/
/**
 * Created by Bobby Irawan on 10/11/20.
 *//*

@ExperimentalCoroutinesApi
class GetDetailMovieUseCaseTest : BaseTest() {

    @MockK
    private lateinit var mockRepository: Repository

    private lateinit var usecase: GetDetailMovieUseCase

    override fun setup() {
        super.setup()
        usecase = GetDetailMovieUseCase(mockRepository)
    }

    @Test
    fun execute() {
        lateinit var expectedResult: MovieDetailModelView
        Result.Success(movieDetail).handleResult(successDataBlock = {
            expectedResult = it
        })

        // Given
        coEvery { mockRepository.getDetailMovie(MockData.movieId) } returns flowOf(
            successResourceMovieDetailResponse
        )
        lateinit var result: MovieDetailModelView

        // When
        runBlocking {
            usecase.execute(MockData.movieId).collect {
                it.handleResult(successDataBlock = {
                    result = it
                })
            }
            coVerify { mockRepository.getDetailMovie(MockData.movieId) }

            // Then
            coVerifySequence {
                mockRepository.getDetailMovie(MockData.movieId)
            }
            assertEquals(MockData.imdbId, result.imdbId)
            assertEquals(
                expectedResult.imdbId,
                result.imdbId
            )
        }
    }
}*/
