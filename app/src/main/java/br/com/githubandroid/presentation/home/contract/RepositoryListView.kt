package br.com.githubandroid.presentation.home.contract

import br.com.githubandroid.domain.model.Repository
import br.com.githubandroid.domain.model.RepositoryResponse
import br.com.githubandroid.presentation.base.BaseView

/**
 * Created by pedrohenrique on 19/08/17.
 */
interface RepositoryListView: BaseView<RepositoryPresenter> {
    fun showRepositories(repositories: RepositoryResponse)
}