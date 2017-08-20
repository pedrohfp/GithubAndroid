package br.com.githubandroid.presentation.home

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.githubandroid.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.Espresso.onView
import br.com.githubandroid.domain.model.Owner
import br.com.githubandroid.domain.model.Repository
import br.com.githubandroid.domain.model.RepositoryResponse
import br.com.githubandroid.settings.idling.ElapsedTimeIdlingResource
import br.com.githubandroid.presentation.application.GithubApplication
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import retrofit2.converter.gson.GsonConverterFactory


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

        onView(withId(R.id.search_button)).perform(click())
        onView(withId(R.id.search_src_text)).perform(replaceText("Android"), closeSoftKeyboard())

        val idlingResource = ElapsedTimeIdlingResource(5000)

        Espresso.registerIdlingResources(idlingResource)

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        Espresso.unregisterIdlingResources(idlingResource)
    }

}
