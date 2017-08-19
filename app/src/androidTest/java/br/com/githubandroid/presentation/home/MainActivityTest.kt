package br.com.githubandroid.presentation.home

import android.support.test.espresso.Espresso
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.githubandroid.R
import kotlinx.android.synthetic.main.fragment_repository_list.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.Espresso.onView
import android.support.v7.widget.SearchView
import android.view.KeyEvent
import android.widget.AutoCompleteTextView
import br.com.githubandroid.idling.ElapsedTimeIdlingResource


/**
 * Created by pedrohenrique on 19/08/17.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest{

    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun testSearchGithubRepositories(){
        onView(withId(R.id.searchView)).perform(click())
        onView(withId(android.support.design.R.id.search_src_text)).perform(typeText("Android"))

        val idlingResource = ElapsedTimeIdlingResource(5000)

        Espresso.registerIdlingResources(idlingResource)

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        Espresso.unregisterIdlingResources(idlingResource)

    }

}
