package bobby.irawan.moviecatalogue.presentation.detail.movie

import android.content.Context
import bobby.irawan.movieapp.helper.BaseTest
import bobby.irawan.movieapp.helper.ObserverHelper
import bobby.irawan.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase
import bobby.irawan.moviecatalogue.di.dataModule
import bobby.irawan.moviecatalogue.di.domainModule
import bobby.irawan.moviecatalogue.di.presentationModule
import bobby.irawan.moviecatalogue.helper.MockData
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verifySequence
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

/**
 * Created by Bobby Irawan on 02/11/20.
 */
@ExperimentalCoroutinesApi
class MovieDetailViewModelTest : BaseTest(), KoinTest {

    @MockK
    private lateinit var mockUseCase: MovieCatalogueUseCase

    private lateinit var viewModel: MovieDetailViewModel

    private val mockContext = mockk<Context>(relaxed = true)

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        androidContext(mockContext)
        modules(presentationModule, domainModule, dataModule)
    }

    override fun setup() {
        super.setup()
        viewModel = MovieDetailViewModel(mockUseCase)
    }

//    @Test
//    fun getMovieDetail() {
//        val observer = ObserverHelper.getMockObserver<SimpleResult<MovieDetailDomainModel>>()
//        viewModel.movieDetail.observeForever(observer)
//        val expectedResult = MockData.successResultMovieDetail
//
//        // Given
//        coEvery { mockUseCase.getDetailMovie(movieId) } returns MockData.successMovieDetailResult
//
//        // When
//        val spyIntent: Intent = spyk(Intent())
//        every { spyIntent.getIntExtra(MovieDetailActivity.EXTRA_MOVIE_ID, 0) } returns movieId
//        viewModel.getFromBundle(spyIntent)
//
//        runBlocking {
//            viewModel.getMovieDetail()
//            coVerify { mockUseCase.getDetailMovie(movieId) }
//
//            // Then
//            verifySequence {
//                observer.onChanged(viewModel.movieDetail.value)
//            }
//            assertEquals(
//                expectedResult,
//                viewModel.movieDetail.value
//            )
//        }
//    }

    @Test
    fun generateMovieGenre() {
        val observer = ObserverHelper.getMockObserver<String>()
        viewModel.movieGenre.observeForever(observer)
        val expectedResult = "Drama, Drama"

        viewModel.generateMovieGenre(listOf(MockData.genre, MockData.genre))

        verifySequence {
            observer.onChanged(viewModel.movieGenre.value)
        }
        assertEquals(
            expectedResult,
            viewModel.movieGenre.value
        )
    }

    @Test
    fun generateTvShowGenreFromEmptyList() {
        val observer = ObserverHelper.getMockObserver<String>()
        viewModel.movieGenre.observeForever(observer)
        val expectedResult = ""

        viewModel.generateMovieGenre(listOf())

        assertEquals(
            null,
            viewModel.movieGenre.value
        )
    }
}