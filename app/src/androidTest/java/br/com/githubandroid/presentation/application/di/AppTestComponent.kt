package br.com.githubandroid.presentation.application.di

import br.com.githubandroid.presentation.home.di.RepositoryComponent
import br.com.githubandroid.presentation.home.di.RepositoryListModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by pedrohenrique on 21/08/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkTestModule::class))
interface AppTestComponent: AppComponent{
}