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
    fun loadTabButton() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        delayTwoSecond()
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        delayTwoSecond()
        onView(withId(R.id.view_pager)).perform(swipeRight())
        onView(withText(R.string.tv_show)).perform(click())
        onView(withText(R.string.movies)).perform(click())
    }

    @Test
    fun loadMovies() {
        delayTwoSecond()
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        delayTwoSecond()
        onView(withText("Movies")).perform(click())
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        delayTwoSecond()
        onView(withId(R.id.title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.title_detail)).check(matches(withText(dummyMovie[0].title)))

        onView(withId(R.id.overview)).check(matches(isDisplayed()))
        onView(withId(R.id.overview)).check(matches(withText(dummyMovie[0].overview)))

        onView(withId(R.id.images_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.share)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvData() {
        delayTwoSecond()
        onView(withText(R.string.tv_show)).perform(click())

        delayTwoSecond()
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShow.size
            )
        )
    }

    @Test
    fun loadDetailTvShow() {
        delayTwoSecond()
        onView(withText("Tv Show")).perform(click())

        delayTwoSecond()
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        delayTwoSecond()
        onView(withId(R.id.title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.title_detail)).check(matches(withText(dummyTvShow[0].name)))

        onView(withId(R.id.overview)).check(matches(isDisplayed()))
        onView(withId(R.id.overview)).check(matches(withText(dummyTvShow[0].overview)))

        onView(withId(R.id.images_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.share)).check(matches(isDisplayed()))
    }

    private fun delayTwoSecond() {
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}