package br.com.githubandroid.presentation.home

import br.com.githubandroid.domain.interactor.repository.GetRepositoryUseCase
import br.com.githubandroid.domain.model.Repository
import br.com.githubandroid.presentation.home.contract.MainActivityView
import br.com.githubandroid.presentation.home.contract.RepositoryListView
import br.com.githubandroid.presentation.home.contract.RepositoryPresenter
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * Created by pedrohenrique on 19/08/17.
 */
class RepositoryListPresenterImpl: RepositoryPresenter {

    var mActivityView: MainActivityView
    var mRepositoryListView: RepositoryListView
    var mGetRepositoryUseCase: GetRepositoryUseCase

    @Inject
    constructor(mActivityView: MainActivityView, mRepositoryListView: RepositoryListView, mGetRepositoryUseCase: GetRepositoryUseCase) {
        this.mActivityView = mActivityView
        this.mRepositoryListView = mRepositoryListView
        this.mGetRepositoryUseCase = mGetRepositoryUseCase

        mActivityView.setPresenter(this)
        mRepositoryListView.setPresenter(this)
    }

    override fun loadGithubRepositories(query: String) {
        mGetRepositoryUseCase.execute(RepositoryListSubscriber(), query)
    }

    override fun successLoadGithubRepositories(repositories: ArrayList<Repository>) {
        mRepositoryListView.showRepositories(repositories)
    }

    inner class RepositoryListSubscriber: DisposableObserver<ArrayList<Repository>>(){
        override fun onNext(t: ArrayList<Repository>) {
            successLoadGithubRepositories(t)
        }

        override fun onComplete() {

        }

        override fun onError(e: Throwable) {

        }
    }
}