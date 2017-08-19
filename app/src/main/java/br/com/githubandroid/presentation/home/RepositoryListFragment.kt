package br.com.githubandroid.presentation.home

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_repository_list.*

import br.com.githubandroid.R
import br.com.githubandroid.domain.model.Repository
import br.com.githubandroid.presentation.home.contract.RepositoryListView
import br.com.githubandroid.presentation.home.contract.RepositoryPresenter
import br.com.githubandroid.presentation.utils.RxSearch
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class RepositoryListFragment : Fragment(), RepositoryListView {

    //Presenter
    lateinit var mPresenter: RepositoryPresenter

    //Adapter - RecyclerView
    lateinit var mAdapter: RepositoryListAdapter

    companion object {
        /**
         * new instance pattern for fragment
         */
        @JvmStatic
        fun newInstance(): RepositoryListFragment {
            return RepositoryListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_repository_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)

        recyclerView.layoutManager = layoutManager

        mAdapter = RepositoryListAdapter(activity)
        mAdapter.setRepositories(arrayListOf())

        recyclerView.adapter = mAdapter

        RxSearch.instance.fromSearchView(searchView)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ query ->
                    if(query!!.isEmpty()) {
                        mAdapter.repositoryList.clear()
                        mAdapter.notifyDataSetChanged()
                    }else{
                        mPresenter.loadGithubRepositories(query)
                    }
                })
    }

    override fun setPresenter(presenter: RepositoryPresenter) {
        mPresenter = presenter
    }

    override fun showRepositories(repositories: ArrayList<Repository>) {
        mAdapter.repositoryList.clear()
        mAdapter.repositoryList.addAll(repositories)
        mAdapter.notifyDataSetChanged()
    }

}
