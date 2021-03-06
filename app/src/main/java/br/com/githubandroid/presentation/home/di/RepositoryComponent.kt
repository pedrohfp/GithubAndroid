package br.com.githubandroid.presentation.home.di

import br.com.githubandroid.presentation.application.di.AppComponent
import br.com.githubandroid.presentation.home.MainActivity
import br.com.githubandroid.presentation.utils.FragmentScoped
import dagger.Component
import dagger.Subcomponent

/**
 * Created by pedrohenrique on 19/08/17.
 */
@FragmentScoped
@Subcomponent(modules = arrayOf(RepositoryListModule::class))
interface RepositoryComponent{
    fun inject(main: MainActivity)
}