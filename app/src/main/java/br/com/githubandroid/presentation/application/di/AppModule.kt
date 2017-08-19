package br.com.githubandroid.presentation.application.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by pedrohenrique on 19/08/17.
 */
@Module
class AppModule{
    val mApplication: Application

    constructor(mApplication: Application){
        this.mApplication = mApplication
    }

    @Provides
    @Singleton
    fun provideApplication(): Application{
        return mApplication
    }
}