package com.giftech.githubuser.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.giftech.githubuser.R
import com.giftech.githubuser.data.User
import com.giftech.githubuser.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val dummyUser = User(
        name = "Galih Indra Firmansyah",
        username = "galihif",
        company = "Universitas Gadjah Mada",
        location = "Serang, Banten"
    )

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadDetailUserAndBack(){
        onView(withId(R.id.rv_user)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))

        onView(withId(R.id.tv_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_name)).check(matches(withText(dummyUser.name)))

        onView(withId(R.id.tv_username)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_username)).check(matches(withText(dummyUser.username)))

        onView(withId(R.id.tv_company)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_company)).check(matches(withText(dummyUser.company)))

        onView(withId(R.id.tv_location)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_location)).check(matches(withText(dummyUser.location)))
    }
}