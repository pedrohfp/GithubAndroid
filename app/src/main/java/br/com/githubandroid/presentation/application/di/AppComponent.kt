package br.com.githubandroid.presentation.application.di

import br.com.githubandroid.presentation.home.di.RepositoryComponent
import br.com.githubandroid.presentation.home.di.RepositoryListModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by pedrohenrique on 19/08/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface AppComponent {
    fun getRetrofit(): Retrofit
    fun plusRepository(module: RepositoryListModule): RepositoryComponent
}