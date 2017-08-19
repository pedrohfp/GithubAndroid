package br.com.githubandroid.data.repositories.source

import br.com.githubandroid.data.repositories.contract.RepositoryDataSource
import br.com.githubandroid.data.rest.RepositoryApi
import br.com.githubandroid.domain.model.Owner
import br.com.githubandroid.domain.model.Repository
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import org.json.JSONObject
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by pedrohenrique on 19/08/17.
 */
class RepositoryRemoteDataSource: RepositoryDataSource {

    val mRetrofit: Retrofit

    @Inject
    constructor(mRetrofit: Retrofit) {
        this.mRetrofit = mRetrofit
    }

    override fun loadGithubRepositories(query: String): Observable<ArrayList<Repository>> {
        return Observable.create {
            e: ObservableEmitter<ArrayList<Repository>> ->

            try{

                var repositoryApi = mRetrofit.create(RepositoryApi::class.java)

                var callBody = repositoryApi.getGitHubRepositories(query)

                var responseBody = callBody.execute()

                val repositoryList = ArrayList<Repository>()

                if(responseBody.isSuccessful){

                    var jsonObject = JSONObject(responseBody.body()?.string())
                    var items = jsonObject.getJSONArray("items")

                    for(i in 0..(items.length() - 1)){
                        val repositoryObject = items.getJSONObject(i)
                        val id = repositoryObject.getInt("id")
                        val name = repositoryObject.getString("name")
                        val fullName = repositoryObject.getString("full_name")

                        val ownerObject = repositoryObject.getJSONObject("owner")
                        val login = ownerObject.getString("login")
                        val ownerId = ownerObject.getInt("id")
                        val avatarUrl = ownerObject.getString("avatar_url")

                        val owner = Owner(ownerId, login, avatarUrl)
                        val repository = Repository(id, name, fullName, owner)

                        repositoryList.add(repository)
                    }

                    e.onNext(repositoryList)
                }

            }catch (error: Exception){
                error.printStackTrace()
                e.onError(error)
            }
        }
    }

}