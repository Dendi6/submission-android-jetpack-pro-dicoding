package com.dendi.filmscatalogs.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.dendi.filmscatalogs.R
import com.dendi.filmscatalogs.utils.DataDummy
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest {
    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTvShow = DataDummy.generateDummyTvShow()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun tabTest() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withId(R.id.view_pager)).perform(swipeRight())
        onView(withText(R.string.tv_show)).perform(click())
        onView(withText(R.string.movies)).perform(click())
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )
        onView(withId(R.id.rv_movies)).perform(swipeDown())
        onView(withId(R.id.rv_movies)).perform(swipeUp())
    }

    @Test
    fun moviesDetail() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.title_detail)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.date_release)).check(matches(isDisplayed()))
        onView(withId(R.id.date_release)).check(matches(withText(dummyMovie[0].dateRelease)))
        onView(withId(R.id.genre_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.genre_duration)).check(matches(withText(dummyMovie[0].genre)))
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
        onView(withId(R.id.overview)).check(matches(withText(dummyMovie[0].overview)))
        onView(withId(R.id.images_detail)).check(matches(isDisplayed()))

        onView(withId(R.id.share)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvData() {
        onView(withText(R.string.tv_show)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShow.size
            )
        )
        onView(withId(R.id.rv_tv_show)).perform(swipeDown())
        onView(withId(R.id.rv_tv_show)).perform(swipeUp())
    }

    @Test
    fun tvShowDetail(){
        onView(withText(R.string.tv_show)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.title_detail)).check(matches(withText(dummyTvShow[0].title)))
        onView(withId(R.id.date_release)).check(matches(isDisplayed()))
        onView(withId(R.id.date_release)).check(matches(withText(dummyTvShow[0].dateRelease)))
        onView(withId(R.id.genre_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.genre_duration)).check(matches(withText(dummyTvShow[0].genre)))
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
        onView(withId(R.id.overview)).check(matches(withText(dummyTvShow[0].overview)))
        onView(withId(R.id.images_detail)).check(matches(isDisplayed()))

        onView(withId(R.id.share)).check(matches(isDisplayed()))
    }
}