package br.com.githubandroid.presentation.base

import android.support.v7.app.AppCompatActivity

/**
 * Created by pedrohenrique on 19/08/17.
 */
abstract class BaseActivity<P : BasePresenter> : AppCompatActivity(), BaseView<P> {
    var currentStackName: String? = null
        protected set

    fun updateToolbar(title: String?) {
        try {
            if (title != null) {
                supportActionBar!!.setTitle(title)
            } else {
                supportActionBar!!.setTitle("")
            }
        } catch (e: Exception) {
            // TODO: toolbar do not exist or not binded.
            e.printStackTrace()
        }

    }
}