package br.com.githubandroid.presentation.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import br.com.githubandroid.R
import br.com.githubandroid.presentation.application.GithubApplication
import br.com.githubandroid.presentation.home.contract.MainActivityView
import br.com.githubandroid.presentation.home.contract.RepositoryPresenter
import br.com.githubandroid.presentation.home.di.RepositoryListModule
import br.com.githubandroid.presentation.utils.ActivityUtils
import javax.inject.Inject

class MainActivity : MainActivityView() {

    //Presenter
    lateinit var mPresenter: RepositoryPresenter

    //Fragment
    var mRepositoryListFragment: RepositoryListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRepositoryListFragment =
                supportFragmentManager.findFragmentById(R.id.content) as RepositoryListFragment?

        if(mRepositoryListFragment == null){
            //Create the fragment
            mRepositoryListFragment = RepositoryListFragment.newInstance()

            ActivityUtils.addFragmentToActivity(supportFragmentManager, mRepositoryListFragment!!, R.id.content)
        }

        //Create the presenter
        GithubApplication.mAppComponent
                .plusRepository(RepositoryListModule(this, mRepositoryListFragment!!))
                .inject(this)


    }

    @Inject
    override fun setPresenter(presenter: RepositoryPresenter) {
        mPresenter = presenter
    }
}
