package br.com.githubandroid.application

import android.app.Application
import br.com.githubandroid.application.di.AppTestComponent
import br.com.githubandroid.application.di.DaggerAppTestComponent
import br.com.githubandroid.application.di.NetworkTestModule
import br.com.githubandroid.presentation.application.GithubApplication
import br.com.githubandroid.presentation.application.di.AppComponent
import br.com.githubandroid.presentation.application.di.AppModule
import br.com.githubandroid.presentation.application.di.DaggerAppComponent
import br.com.githubandroid.presentation.application.di.NetworkModule
import okhttp3.mockwebserver.MockWebServer

/**
 * Created by pedrohenrique on 20/08/17.
 */
class TestApplication: GithubApplication(){

    companion object {
        @JvmStatic lateinit var mAppComponent: AppTestComponent
    }


    override fun onCreate() {
        super.onCreate()
        mAppComponent = initDagger()

    }

    override fun initDagger(): AppTestComponent {
        return DaggerAppTestComponent.builder()
                .appModule(AppModule(this))
                .networkTestModule(NetworkTestModule())
                .build()
    }

    fun getComponent(): AppTestComponent{
        return mAppComponent
    }
}
