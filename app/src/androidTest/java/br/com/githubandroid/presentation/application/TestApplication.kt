package br.com.githubandroid.presentation.application

import br.com.githubandroid.presentation.application.di.*
import okhttp3.mockwebserver.MockWebServer

/**
 * Created by pedrohenrique on 21/08/17.
 */
class TestApplication: GithubApplication(){

    companion object {
        @JvmStatic lateinit var mAppComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()


    }

    override fun initDagger(): AppComponent {
        return DaggerAppTestComponent.builder()
                .appModule(AppModule(this))
                .networkTestModule(NetworkTestModule(""))
                .build()
    }

}