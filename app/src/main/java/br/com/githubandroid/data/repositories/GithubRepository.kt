package br.com.githubandroid.data.repositories

import br.com.githubandroid.data.repositories.contract.RepositoryDataSource
import br.com.githubandroid.domain.model.Repository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by pedrohenrique on 19/08/17.
 */
@Singleton
class GithubRepository : RepositoryDataSource {

    private val mRepositoryRemoteDataSource: RepositoryDataSource

    @Inject
    constructor(mRepositoryRemoteDataSource: RepositoryDataSource) {
        this.mRepositoryRemoteDataSource = mRepositoryRemoteDataSource
    }

    override fun loadGithubRepositories(query: String): Observable<ArrayList<Repository>> {
        return mRepositoryRemoteDataSource.loadGithubRepositories(query)
    }
}