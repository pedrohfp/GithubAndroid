package br.com.githubandroid.presentation.home.di

import br.com.githubandroid.data.repositories.GithubRepository
import br.com.githubandroid.data.repositories.source.RepositoryRemoteDataSource
import br.com.githubandroid.domain.interactor.repository.GetRepositoryUseCase
import br.com.githubandroid.presentation.home.RepositoryListPresenterImpl
import br.com.githubandroid.presentation.home.contract.MainActivityView
import br.com.githubandroid.presentation.home.contract.RepositoryListView
import br.com.githubandroid.presentation.home.contract.RepositoryPresenter
import br.com.githubandroid.presentation.utils.FragmentScoped
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by pedrohenrique on 20/08/17.
 */
class RepositoryListModuleTest: RepositoryListModule{
    override val mActivityView: MainActivityView
    override val mRepositoryListView: RepositoryListView

    constructor(mActivityView: MainActivityView, mRepositoryList: RepositoryListView) : super(mActivityView, mRepositoryList) {
        this.mActivityView = mActivityView
        this.mRepositoryListView = mRepositoryList
    }

    override fun provideActivityView(): MainActivityView {
        return mActivityView
    }

    override fun provideRepositoryListView(): RepositoryListView {
        return mRepositoryListView
    }

    override fun provideRepositoryPresenter(presenter: RepositoryListPresenterImpl): RepositoryPresenter {
        return presenter
    }

    override fun provideGetRepositoryUseCase(repository: GithubRepository): GetRepositoryUseCase {
        return GetRepositoryUseCase(repository)
    }

    override fun provideGithubRepository(mRepositoryRemoteDataSource: RepositoryRemoteDataSource): GithubRepository {
        return mock()
    }

    override fun provideRepositoryRemoteDataSource(retrofit: Retrofit): RepositoryRemoteDataSource {
        return mock()
    }
}