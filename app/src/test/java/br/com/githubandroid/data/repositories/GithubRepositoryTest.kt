package br.com.githubandroid.data.repositories

import br.com.githubandroid.BuildConfig
import br.com.githubandroid.data.repositories.contract.RepositoryDataSource
import br.com.githubandroid.data.repositories.source.RepositoryRemoteDataSource
import br.com.githubandroid.domain.model.Owner
import br.com.githubandroid.presentation.application.GithubApplication
import com.nhaarman.mockito_kotlin.any
import io.reactivex.subscribers.TestSubscriber
import org.junit.After
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import br.com.githubandroid.domain.model.Repository
import br.com.githubandroid.domain.model.RepositoryResponse
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

/**
 * Created by pedrohenrique on 19/08/17.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(25))
class GithubRepositoryTest {

    lateinit var mGithubRepository: GithubRepository
    lateinit var mRepositoryDataSource: RepositoryDataSource
    lateinit var mRetrofit: Retrofit

    @Before
    fun setUp() {

        val owner = Owner(0, "test", "url")
        var repository = Repository(0, "name", "fullName", owner)

        val repositoryList = arrayListOf<Repository>(repository, repository)

        var repositoryResponse = RepositoryResponse(repositoryList)

        val server = MockWebServer()
        server.enqueue(MockResponse().setBody(Gson().toJson(repositoryResponse)))

        mRetrofit = Retrofit.Builder()
                .baseUrl(server.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        mRepositoryDataSource = RepositoryRemoteDataSource(mRetrofit)

        mGithubRepository = GithubRepository(mRepositoryDataSource)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun testLoadGithubRepositories(){
        val subscriber: TestSubscriber<ArrayList<Repository>> = TestSubscriber.create()
        val repositoryList: ArrayList<Repository> = arrayListOf()
        mGithubRepository.loadGithubRepositories("Android").subscribe({t: RepositoryResponse ->
            repositoryList.addAll(t.mItems!!)
        })
        assertNotNull(repositoryList)
        assertTrue(0 != repositoryList.size)
        subscriber.assertNoErrors()
    }
}