package br.com.githubandroid.application.di

import br.com.githubandroid.presentation.application.di.AppComponent
import br.com.githubandroid.presentation.application.di.AppModule
import br.com.githubandroid.presentation.application.di.NetworkModule
import br.com.githubandroid.presentation.home.MainActivityTest
import br.com.githubandroid.presentation.home.di.RepositoryComponent
import br.com.githubandroid.presentation.home.di.RepositoryListModule
import br.com.githubandroid.presentation.home.di.RepositoryListModuleTest
import br.com.githubandroid.presentation.home.di.RepositoryTestComponent
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by pedrohenrique on 20/08/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkTestModule::class))
interface AppTestComponent: AppComponent{
    override fun getRetrofit(): Retrofit
    override fun plusRepository(module: RepositoryListModule): RepositoryTestComponent
}