package br.com.githubandroid.presentation.application

import android.app.Application
import br.com.githubandroid.presentation.application.di.AppComponent
import br.com.githubandroid.presentation.application.di.AppModule
import br.com.githubandroid.presentation.application.di.DaggerAppComponent
import br.com.githubandroid.presentation.application.di.NetworkModule

/**
 * Created by pedrohenrique on 19/08/17.
 */
open class GithubApplication: Application(){

    companion object {
        @JvmStatic lateinit var mAppComponent: AppComponent
        @JvmStatic var mBaseUrl: String = "https://api.github.com/"
    }

    override fun onCreate() {
        super.onCreate()

        mAppComponent = initDagger()
    }

    open fun initDagger(): AppComponent{
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule(mBaseUrl))
                .build()
    }

    open fun setComponent(component: AppComponent){
        mAppComponent = component
    }

}