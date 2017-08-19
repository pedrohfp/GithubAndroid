package br.com.githubandroid.domain.interactor.repository

import br.com.githubandroid.data.repositories.GithubRepository
import br.com.githubandroid.domain.interactor.base.UseCase
import br.com.githubandroid.domain.model.Repository
import br.com.githubandroid.domain.model.RepositoryResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by pedrohenrique on 19/08/17.
 */
class GetRepositoryUseCase @Inject constructor(val mGithubRepository: GithubRepository): UseCase<RepositoryResponse, String>() {

    override fun getObservable(params: String): Observable<RepositoryResponse> {
        return mGithubRepository.loadGithubRepositories(params)
    }

}