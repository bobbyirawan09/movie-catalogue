package bobby.irawan.moviecatalogue.domain.home

import bobby.irawan.movieapp.helper.BaseTest
import bobby.irawan.movieapp.helper.MockData
import bobby.irawan.movieapp.helper.MockData.tvShow
import bobby.irawan.movieapp.helper.MockData.tvShowId
import bobby.irawan.moviecatalogue.data.repository.Repository
import bobby.irawan.moviecatalogue.domain.commons.Result
import bobby.irawan.moviecatalogue.presentation.model.TvShowModelView
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
class GetTvShowUseCaseTest : BaseTest() {

    @MockK
    private lateinit var mockRepository: Repository

    private lateinit var usecase: GetTvShowUseCase

    override fun setup() {
        super.setup()
        usecase = GetTvShowUseCase(mockRepository)
    }

    @Test
    fun execute() {
        lateinit var expectedResult: List<TvShowModelView>
        Result.Success(listOf(tvShow)).handleResult(successDataBlock = {
            expectedResult = it
        })

        // Given
        coEvery { mockRepository.getPopularTvShow(tvShowId) } returns flowOf(MockData.successResourceTvShowResponse)
        lateinit var result: List<TvShowModelView>

        // When
        runBlocking {
            usecase.execute(tvShowId).collect {
                it.handleResult(successDataBlock = {
                    result = it
                })
            }
            coVerify { mockRepository.getPopularTvShow(tvShowId) }

            // Then
            coVerifySequence {
                mockRepository.getPopularTvShow(tvShowId)
            }
            assertEquals(tvShowId, result.first().id)
            assertEquals(
                expectedResult.first().id,
                result.first().id
            )
        }
    }
}