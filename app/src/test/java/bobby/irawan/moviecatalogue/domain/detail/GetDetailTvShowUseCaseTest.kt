package bobby.irawan.moviecatalogue.domain.detail

import bobby.irawan.movieapp.helper.BaseTest
import bobby.irawan.movieapp.helper.MockData
import bobby.irawan.moviecatalogue.data.repository.Repository
import bobby.irawan.moviecatalogue.domain.commons.Result
import bobby.irawan.moviecatalogue.presentation.model.TvShowDetailModelView
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
class GetDetailTvShowUseCaseTest : BaseTest() {

    @MockK
    private lateinit var mockRepository: Repository

    private lateinit var usecase: GetDetailTvShowUseCase

    override fun setup() {
        super.setup()
        usecase = GetDetailTvShowUseCase(mockRepository)
    }

    @Test
    fun execute() {
        lateinit var expectedResult: TvShowDetailModelView
        Result.Success(MockData.tvShowDetail).handleResult(successDataBlock = {
            expectedResult = it
        })

        // Given
        coEvery { mockRepository.getDetailTvShow(MockData.tvShowId) } returns flowOf(MockData.successResourceTvShowDetailResponse)
        lateinit var result: TvShowDetailModelView

        // When
        runBlocking {
            usecase.execute(MockData.tvShowId).collect {
                it.handleResult(successDataBlock = {
                    result = it
                })
            }
            coVerify { mockRepository.getDetailTvShow(MockData.tvShowId) }

            // Then
            coVerifySequence {
                mockRepository.getDetailTvShow(MockData.tvShowId)
            }
            assertEquals(MockData.tvShowId, result.id)
            assertEquals(
                expectedResult.id,
                result.id
            )
        }
    }
}