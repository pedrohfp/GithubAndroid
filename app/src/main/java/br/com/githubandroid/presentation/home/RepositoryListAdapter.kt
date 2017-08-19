package br.com.githubandroid.presentation.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.githubandroid.R
import br.com.githubandroid.domain.model.Repository
import com.squareup.picasso.Picasso

/**
 * Created by pedrohenrique on 19/08/17.
 */
class RepositoryListAdapter(val context: Context) : RecyclerView.Adapter<RepositoryListAdapter.ViewHolder>(){

    lateinit var repositoryList: ArrayList<Repository>

    fun setRepositories(repositories: ArrayList<Repository>){
        repositoryList = repositories
    }

    override fun getItemCount(): Int{
        return repositoryList.size
    }

    override fun onBindViewHolder(holder: RepositoryListAdapter.ViewHolder, position: Int) {
        holder.bindItems(repositoryList[position], context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RepositoryListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.repository_list_item, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(repository: Repository, context: Context){
            val itemImageView = itemView.findViewById<ImageView>(R.id.itemImage)
            val itemNameTextView = itemView.findViewById<TextView>(R.id.itemName)

            Picasso.with(context).load(repository.mOwner.mAvatarUrl).into(itemImageView)
            itemNameTextView.text = repository.mName
        }
    }

}