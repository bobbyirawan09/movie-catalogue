package bobby.irawan.moviecatalogue.presentation.detail.tvshow

import bobby.irawan.movieapp.helper.BaseTest
import bobby.irawan.movieapp.helper.ObserverHelper
import bobby.irawan.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase
import bobby.irawan.moviecatalogue.di.domainModule
import bobby.irawan.moviecatalogue.di.presentationModule
import bobby.irawan.moviecatalogue.helper.MockData.genre
import io.mockk.impl.annotations.MockK
import io.mockk.verifySequence
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

/**
 * Created by Bobby Irawan on 02/11/20.
 */
@ExperimentalCoroutinesApi
class TvShowDetailViewModelTest : BaseTest(), KoinTest {

    @MockK
    private lateinit var mockUseCase: MovieCatalogueUseCase

    private lateinit var viewModel: TvShowDetailViewModel

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(presentationModule, domainModule)
    }

    override fun setup() {
        super.setup()
        viewModel = TvShowDetailViewModel(mockUseCase)
    }

//    @Test
//    fun getTvShowDetail() {
//        val observer = ObserverHelper.getMockObserver<SimpleResult<TvShowDetailDomainModel>>()
//        viewModel.tvShowDetail.observeForever(observer)
//        val expectedResult = MockData.successResultTvShowDetail
//
//        // Given
//        coEvery { mockUseCase.getDetailTvShow(tvShowId) } returns MockData.successTvShowDetailResult
//
//        // When
//        val spyIntent: Intent = spyk(Intent())
//        every { spyIntent.getIntExtra(EXTRA_TV_SHOW_ID, 0) } returns tvShowId
//        viewModel.getFromBundle(spyIntent)
//
//        runBlocking {
//            viewModel.getTvShowDetail()
//            coVerify { mockUseCase.getDetailTvShow(tvShowId) }
//
//            assertEquals(
//                expectedResult,
//                viewModel.tvShowDetail.value
//            )
//
//        }
//    }

    @Test
    fun generateTvShowGenre() {
        val observer = ObserverHelper.getMockObserver<String>()
        viewModel.tvShowGenre.observeForever(observer)
        val expectedResult = "Drama, Drama"

        viewModel.generateTvShowGenre(listOf(genre, genre))

        verifySequence {
            observer.onChanged(viewModel.tvShowGenre.value)
        }
        assertEquals(
            expectedResult,
            viewModel.tvShowGenre.value
        )
    }

    @Test
    fun generateTvShowGenreFromEmptyList() {
        val observer = ObserverHelper.getMockObserver<String>()
        viewModel.tvShowGenre.observeForever(observer)
        val expectedResult = ""

        viewModel.generateTvShowGenre(listOf())

        assertEquals(
            null,
            viewModel.tvShowGenre.value
        )
    }
}