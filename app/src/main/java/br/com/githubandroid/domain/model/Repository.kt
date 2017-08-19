package br.com.githubandroid.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pedrohenrique on 19/08/17.
 */
class Repository{
    @SerializedName("id")
    var mId: Int
    @SerializedName("name")
    var mName: String
    @SerializedName("full_name")
    var mFullName: String
    @SerializedName("owner")
    var mOwner: Owner

    constructor(mId: Int, mName: String, mFullName: String, mOwner: Owner) {
        this.mId = mId
        this.mName = mName
        this.mFullName = mFullName
        this.mOwner = mOwner
    }

    override fun toString(): String {
        return "Repository(mId=$mId, mName='$mName', mFullName='$mFullName', mOwner=$mOwner)"
    }


}