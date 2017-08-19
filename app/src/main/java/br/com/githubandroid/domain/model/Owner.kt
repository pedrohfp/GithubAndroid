package br.com.githubandroid.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pedrohenrique on 19/08/17.
 */
class Owner{
    @SerializedName("id")
    var mId: Int
    @SerializedName("login")
    var mLogin: String
    @SerializedName("avatar_url")
    var mAvatarUrl: String

    constructor(mId: Int, mLogin: String, mAvatarUrl: String) {
        this.mId = mId
        this.mLogin = mLogin
        this.mAvatarUrl = mAvatarUrl
    }

    override fun toString(): String {
        return "Owner(mId=$mId, mLogin='$mLogin', mAvatarUrl='$mAvatarUrl')"
    }


}