package br.com.githubandroid.presentation.home

import android.support.annotation.NonNull
import br.com.githubandroid.data.repositories.GithubRepository
import br.com.githubandroid.domain.interactor.repository.GetRepositoryUseCase
import br.com.githubandroid.domain.model.Repository
import br.com.githubandroid.domain.model.RepositoryResponse
import br.com.githubandroid.presentation.home.contract.MainActivityView
import br.com.githubandroid.presentation.home.contract.RepositoryListView
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

/**
 * Created by pedrohenrique on 19/08/17.
 */
class RepositoryListPresenterImplTest {

    lateinit var mActivityView: MainActivityView
    lateinit var mRepositoryListView: RepositoryListView
    lateinit var mGithubRepository: GithubRepository
    lateinit var mGetRepositoryUseCase: GetRepositoryUseCase
    lateinit var mPresenter: RepositoryListPresenterImpl
    lateinit var mRepositoryResponse: RepositoryResponse

    @Before
    fun setup(){
        mActivityView = mock()
        mRepositoryListView = mock()
        mGithubRepository = mock()
        mRepositoryResponse = mock()
        mGetRepositoryUseCase = GetRepositoryUseCase(mGithubRepository)

        mPresenter = RepositoryListPresenterImpl(mActivityView, mRepositoryListView, mGetRepositoryUseCase)

        val immediate = object : Scheduler() {
            override fun scheduleDirect(@NonNull run: Runnable, delay: Long, @NonNull unit: TimeUnit): Disposable {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }


    @After
    fun tearDown() {
        RxAndroidPlugins.reset()
    }

    @Test
    fun testSuccessLoadGithubRepositories(){
        whenever(mGetRepositoryUseCase.mGithubRepository.loadGithubRepositories(any())).thenReturn(Observable.just(mRepositoryResponse))
        mPresenter.loadGithubRepositories("Android")
        verify(mRepositoryListView).showRepositories(mRepositoryResponse)
    }

}