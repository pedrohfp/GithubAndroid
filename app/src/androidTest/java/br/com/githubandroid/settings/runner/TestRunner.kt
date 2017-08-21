package br.com.githubandroid.settings.runner

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.test.runner.AndroidJUnitRunner
import br.com.githubandroid.application.TestApplication
import com.github.tmurakami.dexopener.DexOpenerAndroidJUnitRunner

/**
 * Created by pedrohenrique on 20/08/17.
 */
class TestRunner: DexOpenerAndroidJUnitRunner(){

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, TestApplication::class.java.name, context)
    }
}