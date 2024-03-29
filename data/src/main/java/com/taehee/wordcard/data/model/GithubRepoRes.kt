package com.taehee.wordcard.data.model

import com.google.gson.annotations.SerializedName
import com.taehee.wordcard.domain.model.GithubRepo

data class GithubRepoRes(
    @SerializedName("name")
    private val _name: String,

    @SerializedName("url")
    private val _url: String,

    @SerializedName("created_at")
    private val _date: String,

    @SerializedName("html_url")
    private val _id: String,

    ) : GithubRepo {
    override val name: String
        get() = _name
    override val url: String
        get() = _url
    override val html_url: String
        get() = _id
}