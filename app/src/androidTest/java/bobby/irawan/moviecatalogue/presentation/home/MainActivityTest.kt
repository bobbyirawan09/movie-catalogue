package bobby.irawan.moviecatalogue.presentation.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import bobby.irawan.moviecatalogue.R
import bobby.irawan.moviecatalogue.presentation.MainActivity
import bobby.irawan.moviecatalogue.utils.CountingIdlingResourceSingleton
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Bobby Irawan on 04/11/20.
 */
class MainActivityTest {
    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance()
            .register(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance()
            .unregister(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @Test
    fun loadMovieItems() {
        onView(allOf(withId(R.id.shimmer), isDisplayed()))
        onView(allOf(withId(R.id.recycler_view_movie), isDisplayed()))
        onView(withId(R.id.recycler_view_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                7
            )
        )
    }

    @Test
    fun loadTvShowItems() {
        onView(withId(R.id.view_pager_home)).perform(swipeLeft())
        onView(allOf(withId(R.id.shimmer), isDisplayed()))
        onView(allOf(withId(R.id.recycler_view_tv_show), isDisplayed()))
        onView(withId(R.id.recycler_view_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                7
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(allOf(withId(R.id.shimmer), isDisplayed()))
        onView(allOf(withId(R.id.recycler_view_movie), isDisplayed()))
        onView(allOf(withId(R.id.recycler_view_movie))).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(allOf(withId(R.id.text_view_minute), not(withText(""))))
        onView(allOf(withId(R.id.text_view_vote_average), not(withText(""))))
        onView(allOf(withId(R.id.text_view_release_year), not(withText(""))))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withId(R.id.view_pager_home)).perform(swipeLeft())
        onView(allOf(withId(R.id.shimmer), isDisplayed()))
        onView(allOf(withId(R.id.recycler_view_tv_show), isDisplayed()))
        onView(allOf(withId(R.id.recycler_view_tv_show))).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(allOf(withId(R.id.text_view_episodes), not(withText(""))))
        onView(allOf(withId(R.id.text_view_vote_average), not(withText(""))))
        onView(allOf(withId(R.id.text_view_release_year), not(withText(""))))
        onView(allOf(withId(R.id.recycler_view_season), isDisplayed()))
    }

    @Test
    fun clickFavoriteDetailMovie() {
        onView(allOf(withId(R.id.shimmer), isDisplayed()))
        onView(allOf(withId(R.id.recycler_view_movie), isDisplayed()))
        onView(allOf(withId(R.id.recycler_view_movie))).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                3,
                click()
            )
        )
        onView(withId(R.id.image_view_favorite)).perform(click())
        onView(withId(R.id.image_view_back)).perform(click())
        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(allOf(withId(R.id.recycler_view_item), isDisplayed()))
    }

    @Test
    fun clickFavoriteDetailTvShow() {
        onView(withId(R.id.view_pager_home)).perform(swipeLeft())
        onView(allOf(withId(R.id.shimmer), isDisplayed()))
        onView(allOf(withId(R.id.recycler_view_tv_show), isDisplayed()))
        onView(allOf(withId(R.id.recycler_view_tv_show))).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                3,
                click()
            )
        )
        onView(withId(R.id.image_view_favorite)).perform(click())
        onView(withId(R.id.image_view_back)).perform(click())
        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(withId(R.id.view_pager_favorite)).perform(swipeLeft())
        onView(allOf(withId(R.id.recycler_view_item), isDisplayed()))
    }

    @Test
    fun loadFavoriteMovie() {
        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(allOf(withId(R.id.recycler_view_item), isDisplayed()))
    }

    @Test
    fun loadFavoriteTvShow() {
        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(withId(R.id.view_pager_favorite)).perform(swipeLeft())
        onView(allOf(withId(R.id.recycler_view_item), isDisplayed()))
    }

    @Test
    fun loadSortedFavoriteMovie() {
        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(allOf(withId(R.id.recycler_view_item), isDisplayed()))
        onView(withId(R.id.button_sort)).perform(click())
        onView(withId(R.id.text_view_descending)).perform(click())
        onView(allOf(withId(R.id.recycler_view_item), isDisplayed()))
    }

    @Test
    fun loadSortedFavoriteTvShow() {
        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(withId(R.id.view_pager_favorite)).perform(swipeLeft())
        onView(allOf(withId(R.id.recycler_view_item), isDisplayed()))
        onView(withId(R.id.button_sort)).perform(click())
        onView(withId(R.id.text_view_descending)).perform(click())
        onView(allOf(withId(R.id.recycler_view_item), isDisplayed()))
    }
}