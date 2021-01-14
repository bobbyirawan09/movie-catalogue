package bobby.irawan.moviecatalogue.presentation.home.movie

import bobby.irawan.movieapp.helper.BaseTest
import bobby.irawan.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Bobby Irawan on 02/11/20.
 */
@ExperimentalCoroutinesApi
class MovieViewModelTest : BaseTest() {

    @MockK
    private lateinit var mockUseCase: MovieCatalogueUseCase

    @MockK(relaxed = true)
    private lateinit var viewModel: MovieViewModel

    override fun setup() {
        super.setup()
        viewModel = MovieViewModel(mockUseCase)
    }

//    @Test
//    fun loadMovieInitial() {
//        val observer = ObserverHelper.getMockObserver<SimpleResult<List<MovieDomainModel>>>()
//        viewModel.movieItems.observeForever(observer)
//        val expectedResult = successResultMovie
//
//        // Given
//        coEvery { mockUseCase.getPopularMovies(initialPage) } returns MockData.successMovieResult
//
//        // When
//        runBlocking {
//            viewModel.loadMovieInitial()
//            coVerify { mockUseCase.getPopularMovies(initialPage) }
//
//            // Then
//            verifySequence {
//                observer.onChanged(viewModel.movieItems.value)
//            }
//            assertEquals(
//                expectedResult,
//                viewModel.movieItems.value
//            )
//        }
//    }
//
//    @Test
//    fun loadMovieNextPage() = runBlocking {
//        val observer = ObserverHelper.getMockObserver<SimpleResult<List<MovieDomainModel>>>()
//        viewModel.movieItems.observeForever(observer)
//        val expectedResult = MockData.successResultMovie
//
//        // Given
//        coEvery { mockUseCase.getPopularMovies(nextPage) } returns MockData.successMovieResult
//
//        // When
//        viewModel.loadMovieNextPage()
//        coVerify { mockUseCase.getPopularMovies(nextPage) }
//
//        // Then
//        verifySequence {
//            observer.onChanged(viewModel.movieItems.value)
//        }
//        assertEquals(
//            expectedResult,
//            viewModel.movieItems.value
//        )
//    }
//
//    @Test
//    fun retryConnectionWhenDataExist() = runBlocking {
//        // Given
//        val observer = ObserverHelper.getMockObserver<SimpleResult<List<MovieDomainModel>>>()
//        viewModel.movieItems.observeForever(observer)
//        val loadingObserver = ObserverHelper.getMockObserver<Boolean>()
//        viewModel.loading().observeForever(loadingObserver)
//        coEvery { mockUseCase.getPopularMovies(initialPage) } returns flowOf(successResultMovie)
//        lateinit var expectedResult: List<MovieDomainModel>
//        viewModel.movies.add(movie)
//
//        // When
//        viewModel.retryConnection()
//
//        //Then
//        coVerify { mockUseCase.getPopularMovies(initialPage) }
//        verifySequence {
//            loadingObserver.onChanged(true)
//            observer.onChanged(viewModel.movieItems.value)
//            loadingObserver.onChanged(false)
//        }
//        viewModel.movieItems.value?.handleResult(successDataBlock = {
//            expectedResult = it
//        })
//        assertEquals(movieId, expectedResult.first().id)
//    }
//
//    @Test
//    fun retryConnectionWhenDataNotExist() = runBlocking {
//        // Given
//        val observer = ObserverHelper.getMockObserver<SimpleResult<List<MovieDomainModel>>>()
//        viewModel.movieItems.observeForever(observer)
//        coEvery { mockUseCase.getPopularMovies(initialPage) } returns flowOf(successResultMovie)
//        lateinit var expectedResult: List<MovieDomainModel>
//
//        // When
//        viewModel.retryConnection()
//
//        //Then
//        coVerify { mockUseCase.getPopularMovies(initialPage) }
//        verifySequence {
//            observer.onChanged(viewModel.movieItems.value)
//        }
//        viewModel.movieItems.value?.handleResult(successDataBlock = {
//            expectedResult = it
//        })
//        assertEquals(movieId, expectedResult.first().id)
//    }
}