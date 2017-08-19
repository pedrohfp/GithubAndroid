package br.com.githubandroid.presentation.home.contract

import br.com.githubandroid.domain.model.Repository
import br.com.githubandroid.presentation.base.BasePresenter

/**
 * Created by pedrohenrique on 19/08/17.
 */
interface RepositoryPresenter : BasePresenter {
    fun loadGithubRepositories(query: String)
    fun successLoadGithubRepositories(repositories: ArrayList<Repository>)
}