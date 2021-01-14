package bobby.irawan.moviecatalogue.presentation.home.tvshow

import bobby.irawan.movieapp.helper.BaseTest
import bobby.irawan.movieapp.helper.MockData
import bobby.irawan.movieapp.helper.ObserverHelper
import com.irawan.dicoding.core.data.common.SimpleResult
import bobby.irawan.moviecatalogue.domain.home.GetTvShowUseCase
import bobby.irawan.moviecatalogue.presentation.model.TvShowModelView
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.verifySequence
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Bobby Irawan on 02/11/20.
 */
@ExperimentalCoroutinesApi
class TvShowViewModelTest : BaseTest() {

    @MockK
    private lateinit var mockUseCase: GetTvShowUseCase

    private lateinit var viewModel: TvShowViewModel

    override fun setup() {
        super.setup()
        viewModel = TvShowViewModel(mockUseCase)
    }

    @Test
    fun loadTvShowInitial() {
        val observer = ObserverHelper.getMockObserver<_root_ide_package_.com.irawan.dicoding.core.data.common.SimpleResult<List<TvShowModelView>>>()
        viewModel.tvShowItems.observeForever(observer)
        val expectedResult = MockData.successResultTvShow

        // Given
        coEvery { mockUseCase.execute(MockData.initialPage) } returns MockData.successTvShowResult

        // When
        runBlocking {
            viewModel.loadTvShowInitial()

            // Then
            verifySequence {
                observer.onChanged(viewModel.tvShowItems.value)
            }
            assertEquals(
                expectedResult,
                viewModel.tvShowItems.value
            )
        }
    }

    @Test
    fun loadTvShowNextPage() = runBlockingTest {
        val observer = ObserverHelper.getMockObserver<_root_ide_package_.com.irawan.dicoding.core.data.common.SimpleResult<List<TvShowModelView>>>()
        viewModel.tvShowItems.observeForever(observer)
        val expectedResult = MockData.successResultTvShow

        // Given
        coEvery { mockUseCase.execute(MockData.nextPage) } returns MockData.successTvShowResult

        // When
        viewModel.loadTvShowNextPage()

        // Then
        verifySequence {
            observer.onChanged(viewModel.tvShowItems.value)
        }
        assertEquals(
            expectedResult,
            viewModel.tvShowItems.value
        )
    }

    @Test
    fun retryConnectionWhenDataExist() = runBlocking {
        // Given
        val observer = ObserverHelper.getMockObserver<_root_ide_package_.com.irawan.dicoding.core.data.common.SimpleResult<List<TvShowModelView>>>()
        viewModel.tvShowItems.observeForever(observer)
        val loadingObserver = ObserverHelper.getMockObserver<Boolean>()
        viewModel.loading().observeForever(loadingObserver)
        coEvery { mockUseCase.execute(MockData.initialPage) } returns flowOf(MockData.successResultTvShow)
        lateinit var expectedResult: List<TvShowModelView>
        viewModel.tvShows.add(MockData.tvShow)

        // When
        viewModel.retryConnection()

        //Then
        coVerify { mockUseCase.execute(MockData.initialPage) }
        verifySequence {
            loadingObserver.onChanged(true)
            observer.onChanged(viewModel.tvShowItems.value)
            loadingObserver.onChanged(false)
        }
        viewModel.tvShowItems.value.handleResult(successDataBlock = {
            expectedResult = it
        })
        assertEquals(MockData.tvShowId, expectedResult.first().id)
    }

    @Test
    fun retryConnectionWhenDataNotExist() = runBlocking {
        // Given
        val observer = ObserverHelper.getMockObserver<_root_ide_package_.com.irawan.dicoding.core.data.common.SimpleResult<List<TvShowModelView>>>()
        viewModel.tvShowItems.observeForever(observer)
        coEvery { mockUseCase.execute(MockData.initialPage) } returns flowOf(MockData.successResultTvShow)
        lateinit var expectedResult: List<TvShowModelView>

        // When
        viewModel.retryConnection()

        //Then
        coVerify { mockUseCase.execute(MockData.initialPage) }
        verifySequence {
            observer.onChanged(viewModel.tvShowItems.value)
        }
        viewModel.tvShowItems.value.handleResult(successDataBlock = {
            expectedResult = it
        })
        assertEquals(MockData.tvShowId, expectedResult.first().id)
    }
}