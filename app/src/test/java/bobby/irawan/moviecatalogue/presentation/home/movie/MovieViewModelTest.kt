package bobby.irawan.moviecatalogue.presentation.home.movie

import bobby.irawan.movieapp.helper.BaseTest
import bobby.irawan.movieapp.helper.MockData
import bobby.irawan.movieapp.helper.MockData.initialPage
import bobby.irawan.movieapp.helper.MockData.movie
import bobby.irawan.movieapp.helper.MockData.movieId
import bobby.irawan.movieapp.helper.MockData.nextPage
import bobby.irawan.movieapp.helper.MockData.successResultMovie
import bobby.irawan.movieapp.helper.ObserverHelper
import com.irawan.dicoding.core.data.common.SimpleResult
import bobby.irawan.moviecatalogue.domain.home.GetMovieUseCase
import bobby.irawan.moviecatalogue.presentation.model.MovieModelView
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.verifySequence
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Bobby Irawan on 02/11/20.
 */
@ExperimentalCoroutinesApi
class MovieViewModelTest : BaseTest() {

    @MockK
    private lateinit var mockUseCase: GetMovieUseCase

    @MockK(relaxed = true)
    private lateinit var viewModel: MovieViewModel

    override fun setup() {
        super.setup()
        viewModel = MovieViewModel(mockUseCase)
    }

    @Test
    fun loadMovieInitial() {
        val observer = ObserverHelper.getMockObserver<_root_ide_package_.com.irawan.dicoding.core.data.common.SimpleResult<List<MovieModelView>>>()
        viewModel.movieItems.observeForever(observer)
        val expectedResult = successResultMovie

        // Given
        coEvery { mockUseCase.execute(initialPage) } returns MockData.successMovieResult

        // When
        runBlocking {
            viewModel.loadMovieInitial()
            coVerify { mockUseCase.execute(initialPage) }

            // Then
            verifySequence {
                observer.onChanged(viewModel.movieItems.value)
            }
            assertEquals(
                expectedResult,
                viewModel.movieItems.value
            )
        }
    }

    @Test
    fun loadMovieNextPage() = runBlocking {
        val observer = ObserverHelper.getMockObserver<_root_ide_package_.com.irawan.dicoding.core.data.common.SimpleResult<List<MovieModelView>>>()
        viewModel.movieItems.observeForever(observer)
        val expectedResult = MockData.successResultMovie

        // Given
        coEvery { mockUseCase.execute(nextPage) } returns MockData.successMovieResult

        // When
        viewModel.loadMovieNextPage()
        coVerify { mockUseCase.execute(nextPage) }

        // Then
        verifySequence {
            observer.onChanged(viewModel.movieItems.value)
        }
        assertEquals(
            expectedResult,
            viewModel.movieItems.value
        )
    }

    @Test
    fun retryConnectionWhenDataExist() = runBlocking {
        // Given
        val observer = ObserverHelper.getMockObserver<_root_ide_package_.com.irawan.dicoding.core.data.common.SimpleResult<List<MovieModelView>>>()
        viewModel.movieItems.observeForever(observer)
        val loadingObserver = ObserverHelper.getMockObserver<Boolean>()
        viewModel.loading().observeForever(loadingObserver)
        coEvery { mockUseCase.execute(initialPage) } returns flowOf(successResultMovie)
        lateinit var expectedResult: List<MovieModelView>
        viewModel.movies.add(movie)

        // When
        viewModel.retryConnection()

        //Then
        coVerify { mockUseCase.execute(initialPage) }
        verifySequence {
            loadingObserver.onChanged(true)
            observer.onChanged(viewModel.movieItems.value)
            loadingObserver.onChanged(false)
        }
        viewModel.movieItems.value.handleResult(successDataBlock = {
            expectedResult = it
        })
        assertEquals(movieId, expectedResult.first().id)
    }

    @Test
    fun retryConnectionWhenDataNotExist() = runBlocking {
        // Given
        val observer = ObserverHelper.getMockObserver<_root_ide_package_.com.irawan.dicoding.core.data.common.SimpleResult<List<MovieModelView>>>()
        viewModel.movieItems.observeForever(observer)
        coEvery { mockUseCase.execute(initialPage) } returns flowOf(successResultMovie)
        lateinit var expectedResult: List<MovieModelView>

        // When
        viewModel.retryConnection()

        //Then
        coVerify { mockUseCase.execute(initialPage) }
        verifySequence {
            observer.onChanged(viewModel.movieItems.value)
        }
        viewModel.movieItems.value.handleResult(successDataBlock = {
            expectedResult = it
        })
        assertEquals(movieId, expectedResult.first().id)
    }
}