package br.com.githubandroid.data.rest

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by pedrohenrique on 19/08/17.
 */
interface RepositoryApi {
    @GET("search/repositories")
    fun getGitHubRepositories(@Query("q") query: String): Call<ResponseBody>
}