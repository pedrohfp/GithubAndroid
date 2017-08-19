package br.com.githubandroid.presentation.home.di

import br.com.githubandroid.data.repositories.GithubRepository
import br.com.githubandroid.data.repositories.source.RepositoryRemoteDataSource
import br.com.githubandroid.domain.interactor.repository.GetRepositoryUseCase
import br.com.githubandroid.presentation.home.RepositoryListPresenterImpl
import br.com.githubandroid.presentation.home.contract.MainActivityView
import br.com.githubandroid.presentation.home.contract.RepositoryListView
import br.com.githubandroid.presentation.home.contract.RepositoryPresenter
import br.com.githubandroid.presentation.utils.FragmentScoped
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by pedrohenrique on 19/08/17.
 */
@Module
class RepositoryListModule{
    val mActivityView: MainActivityView
    val mRepositoryListView: RepositoryListView

    constructor(mActivityView: MainActivityView, mRepositoryList: RepositoryListView) {
        this.mActivityView = mActivityView
        this.mRepositoryListView = mRepositoryList
    }

    @FragmentScoped
    @Provides
    fun provideActivityView(): MainActivityView{
        return mActivityView
    }

    @FragmentScoped
    @Provides
    fun provideRepositoryListView(): RepositoryListView{
        return mRepositoryListView
    }

    @FragmentScoped
    @Provides
    fun provideRepositoryPresenter(presenter: RepositoryListPresenterImpl): RepositoryPresenter {
        return presenter
    }

    @FragmentScoped
    @Provides
    fun provideGetRepositoryUseCase(repository: GithubRepository): GetRepositoryUseCase {
        return GetRepositoryUseCase(repository)
    }

    @FragmentScoped
    @Provides
    fun provideGithubRepository(mRepositoryRemoteDataSource: RepositoryRemoteDataSource): GithubRepository{
        return GithubRepository(mRepositoryRemoteDataSource)
    }

    @FragmentScoped
    @Provides
    fun provideRepositoryRemoteDataSource(retrofit: Retrofit): RepositoryRemoteDataSource{
        return RepositoryRemoteDataSource(retrofit)
    }
}