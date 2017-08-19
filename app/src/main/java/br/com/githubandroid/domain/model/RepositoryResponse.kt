package br.com.githubandroid.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pedrohenrique on 19/08/17.
 */
class RepositoryResponse{

    @SerializedName("items")
    var mItems: ArrayList<Repository>?

    constructor(mItems: ArrayList<Repository>?) {
        this.mItems = mItems
    }

    override fun toString(): String {
        return "RepositoryResponse{" +
                "items=" + mItems +
                '}'
    }

}