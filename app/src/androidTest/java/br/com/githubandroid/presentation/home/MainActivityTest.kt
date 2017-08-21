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
import br.com.githubandroid.data.repositories.GithubRepository
import br.com.githubandroid.domain.model.Owner
import br.com.githubandroid.domain.model.Repository
import br.com.githubandroid.domain.model.RepositoryResponse
import br.com.githubandroid.settings.idling.ElapsedTimeIdlingResource
import br.com.githubandroid.presentation.application.GithubApplication
import br.com.githubandroid.presentation.application.di.AppModule
import br.com.githubandroid.presentation.application.di.DaggerAppTestComponent
import br.com.githubandroid.presentation.application.di.NetworkTestModule
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before


/**
 * Created by pedrohenrique on 19/08/17.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest{

    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule<MainActivity>(MainActivity::class.java, false, false)

    lateinit var mServer: MockWebServer

    @Before
    fun setUp(){

        mServer = MockWebServer()
        mServer.start()

        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val application = instrumentation.targetContext.applicationContext as GithubApplication

        val component = DaggerAppTestComponent.builder()
                .appModule(AppModule(application))
                .networkTestModule(NetworkTestModule(mServer.url("/").toString()))
                .build()

        application.setComponent(component)
    }

    @Test
    fun testSearchGithubRepositories(){

        mActivityRule.launchActivity(null)

        val owner = Owner(0, "test", "url")
        var repository = Repository(0, "name", "fullName", owner)

        val repositoryList = arrayListOf(repository, repository)

        val repositoryResponse = RepositoryResponse(repositoryList)

        mServer.enqueue(MockResponse().setBody(Gson().toJson(repositoryResponse)))

        onView(withId(R.id.search_button)).perform(click())
        onView(withId(R.id.search_src_text)).perform(replaceText("test"), closeSoftKeyboard())
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

}
