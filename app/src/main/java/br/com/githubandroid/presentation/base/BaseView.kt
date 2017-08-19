package br.com.githubandroid.presentation.base

/**
 * Created by pedrohenrique on 19/08/17.
 */
interface BaseView<T> {
    fun setPresenter(presenter: T)
}
