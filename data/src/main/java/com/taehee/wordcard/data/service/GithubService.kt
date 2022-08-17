package com.taehee.wordcard.data.service

import com.taehee.wordcard.data.model.GithubRepoRes
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{owner}/repos")
    suspend fun getRepos(@Path("owner") owner: String): List<GithubRepoRes>
}