package br.com.githubandroid.presentation.home.di

import br.com.githubandroid.presentation.home.MainActivity
import br.com.githubandroid.presentation.home.MainActivityTest
import br.com.githubandroid.presentation.utils.FragmentScoped
import dagger.Subcomponent

/**
 * Created by pedrohenrique on 20/08/17.
 */
@FragmentScoped
@Subcomponent(modules = arrayOf(RepositoryListModule::class))
interface RepositoryTestComponent: RepositoryComponent{
    fun inject(main: MainActivityTest)
}