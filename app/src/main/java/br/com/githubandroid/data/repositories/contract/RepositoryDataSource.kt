package br.com.githubandroid.data.repositories.contract

import br.com.githubandroid.domain.model.Repository
import br.com.githubandroid.domain.model.RepositoryResponse
import io.reactivex.Observable

/**
 * Created by pedrohenrique on 19/08/17.
 */

interface RepositoryDataSource {
    fun loadGithubRepositories(query: String): Observable<RepositoryResponse>
}