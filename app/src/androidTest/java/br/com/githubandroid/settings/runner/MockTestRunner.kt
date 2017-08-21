package br.com.githubandroid.settings.runner

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.test.runner.AndroidJUnitRunner
import br.com.githubandroid.presentation.application.TestApplication
import com.github.tmurakami.dexopener.DexOpenerAndroidJUnitRunner

/**
 * Created by pedrohenrique on 21/08/17.
 */
class MockTestRunner: DexOpenerAndroidJUnitRunner(){

    override fun onCreate(arguments: Bundle?) {
        super.onCreate(arguments)
    }

}
