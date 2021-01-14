package bobby.irawan.moviecatalogue.data.repository

import bobby.irawan.movieapp.helper.BaseTest
import bobby.irawan.movieapp.helper.MockData
import bobby.irawan.movieapp.helper.MockData.dataSourceFactory
import bobby.irawan.movieapp.helper.MockData.favoriteMovie
import bobby.irawan.movieapp.helper.MockData.favoriteTvShow
import bobby.irawan.movieapp.helper.MockData.favoriteTvShowId
import bobby.irawan.movieapp.helper.MockData.initialPage
import bobby.irawan.movieapp.helper.MockData.movieId
import bobby.irawan.movieapp.helper.MockData.tvShowId
import bobby.irawan.moviecatalogue.data.common.Resource
import bobby.irawan.moviecatalogue.data.local.room.FavoriteDao
import bobby.irawan.moviecatalogue.data.remote.movie.MovieApi
import bobby.irawan.moviecatalogue.data.remote.movie.model.MovieDetailResponse
import bobby.irawan.moviecatalogue.data.remote.movie.model.MovieResponse
import bobby.irawan.moviecatalogue.data.remote.tvshow.TvShowApi
import bobby.irawan.moviecatalogue.data.remote.tvshow.model.TvShowDetailResponse
import bobby.irawan.moviecatalogue.data.remote.tvshow.model.TvShowResponse
import bobby.irawan.moviecatalogue.helper.PagedListUtil
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * Created by Bobby Irawan on 09/11/20.
 */
@ExperimentalCoroutinesApi
class RepositoryImplTest : BaseTest() {

    @MockK
    private lateinit var mockMovieApi: MovieApi

    @MockK
    private lateinit var mockTvShowApi: TvShowApi

    @MockK
    private lateinit var mockFavoriteDao: FavoriteDao

    private lateinit var repository: Repository

    override fun setup() {
        super.setup()
        repository = RepositoryImpl(mockMovieApi, mockTvShowApi, mockFavoriteDao)
    }

    @Test
    fun getDetailMovie() {
        val expectedResult = Resource.success(MockData.movieDetailResponse)

        // Given
        coEvery { mockMovieApi.getDetailMovie(movieId) } returns MockData.successMovieDetailResponse
        lateinit var result: Resource<MovieDetailResponse>

        // When
        runBlocking {
            repository.getDetailMovie(movieId).collect {
                result = it
            }
            coVerify { mockMovieApi.getDetailMovie(movieId) }

            // Then
            coVerifySequence {
                repository.getDetailMovie(movieId)
                mockMovieApi.getDetailMovie(movieId)
            }
            assertEquals(movieId, result.data.id)
            assertEquals(
                expectedResult,
                result
            )
        }
    }

    @Test
    fun getDetailTvShow() {
        val expectedResult = Resource.success(MockData.tvShowDetailResponse)

        // Given
        coEvery { mockTvShowApi.getDetailTvShow(tvShowId) } returns MockData.successTvShowDetailResponse
        lateinit var result: Resource<TvShowDetailResponse>

        // When
        runBlocking {
            repository.getDetailTvShow(tvShowId).collect {
                result = it
            }
            coVerify { mockTvShowApi.getDetailTvShow(tvShowId) }

            // Then
            coVerifySequence {
                repository.getDetailTvShow(tvShowId)
                mockTvShowApi.getDetailTvShow(tvShowId)
            }
            assertEquals(tvShowId, result.data.id)
            assertEquals(
                expectedResult,
                result
            )
        }
    }

    @Test
    fun getPopularMovies() {
        val expectedResult = Resource.success(MockData.listMovieResponse)

        // Given
        coEvery { mockMovieApi.getPopularMovies(initialPage) } returns MockData.successMovieResponse
        lateinit var result: Resource<List<MovieResponse>>

        // When
        runBlocking {
            repository.getPopularMovies(initialPage).collect {
                result = it
            }
            coVerify { mockMovieApi.getPopularMovies(initialPage) }

            // Then
            coVerifySequence {
                repository.getPopularMovies(initialPage)
                mockMovieApi.getPopularMovies(initialPage)
            }
            assertEquals(movieId, result.data.first().id)
            assertEquals(
                expectedResult,
                result
            )
        }
    }

    @Test
    fun getPopularTvShow() {
        val expectedResult = Resource.success(MockData.listTvShowResponse)

        // Given
        coEvery { mockTvShowApi.getPopularTvShow(initialPage) } returns MockData.successTvShowResponse
        lateinit var result: Resource<List<TvShowResponse>>

        // When
        runBlocking {
            repository.getPopularTvShow(initialPage).collect {
                result = it
            }
            coVerify { mockTvShowApi.getPopularTvShow(initialPage) }

            // Then
            coVerifySequence {
                repository.getPopularTvShow(initialPage)
                mockTvShowApi.getPopularTvShow(initialPage)
            }
            assertEquals(tvShowId, result.data.first().id)
            assertEquals(
                expectedResult,
                result
            )
        }
    }

    @Test
    fun getFavorites() {
        val expectedResult = listOf(favoriteMovie, favoriteTvShow)
        val result = PagedListUtil.mockPagedList(expectedResult)

        every { mockFavoriteDao.getFavorites() } returns dataSourceFactory

        repository.getFavorites()

        verify {
            mockFavoriteDao.getFavorites()
        }

        assertNotNull(result)
        assertEquals(expectedResult.size.toLong(), result.size.toLong())
    }

    @Test
    fun getFavoriteById() = runBlockingTest {
        coEvery { mockFavoriteDao.getFavoriteById(favoriteTvShowId) } returns favoriteTvShow

        val result = mockFavoriteDao.getFavoriteById(favoriteTvShowId)

        coVerify { mockFavoriteDao.getFavoriteById(favoriteTvShowId) }
        assertEquals(favoriteTvShowId, result.id)
    }

    @Test
    fun getFavoriteTvShow() {
        val expectedResult = listOf(favoriteTvShow)
        val result = PagedListUtil.mockPagedList(expectedResult)

        every { mockFavoriteDao.getFavoriteTvShow() } returns dataSourceFactory

        repository.getFavoriteTvShow(0)

        verify {
            mockFavoriteDao.getFavoriteTvShow()
        }

        assertNotNull(result)
        assertEquals(expectedResult.size.toLong(), result.size.toLong())
    }

    @Test
    fun getFavoriteMovie() {
        val expectedResult = listOf(favoriteMovie)
        val result = PagedListUtil.mockPagedList(expectedResult)

        every { mockFavoriteDao.getFavoriteMovie() } returns dataSourceFactory

        repository.getFavoriteMovie(0)

        verify {
            mockFavoriteDao.getFavoriteMovie()
        }

        assertNotNull(result)
        assertEquals(expectedResult.size.toLong(), result.size.toLong())
    }

    @Test
    fun setFavorite() = runBlockingTest {
        coEvery { mockFavoriteDao.addFavorite(favoriteMovie) } just Runs

        repository.setFavorite(favoriteMovie)

        coVerify {
            mockFavoriteDao.addFavorite(favoriteMovie)
        }
    }

    @Test
    fun deleteFavorite() = runBlockingTest {
        coEvery { mockFavoriteDao.addFavorite(favoriteMovie) } just Runs
        coEvery { mockFavoriteDao.deleteFavorite(favoriteMovie) } returns 1

        val result = repository.deleteFavorite(favoriteMovie)

        coVerify {
            mockFavoriteDao.deleteFavorite(favoriteMovie)
        }
        assertEquals(1, result)
    }
}