package br.com.githubandroid.data.repositories.source

import br.com.githubandroid.data.repositories.contract.RepositoryDataSource
import br.com.githubandroid.data.rest.RepositoryApi
import br.com.githubandroid.domain.model.Owner
import br.com.githubandroid.domain.model.Repository
import br.com.githubandroid.domain.model.RepositoryResponse
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

    override fun loadGithubRepositories(query: String): Observable<RepositoryResponse> {
        return Observable.create {
            e: ObservableEmitter<RepositoryResponse> ->

            try{

                var repositoryApi = mRetrofit.create(RepositoryApi::class.java)

                var callBody = repositoryApi.getGitHubRepositories(query)

                var responseBody = callBody.execute()

                if(responseBody.isSuccessful){

                    e.onNext(responseBody.body()!!)
                }

            }catch (error: Exception){
                error.printStackTrace()
                e.onError(error)
            }
        }
    }

}