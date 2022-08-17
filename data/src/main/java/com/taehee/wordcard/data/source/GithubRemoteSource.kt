package com.taehee.wordcard.data.source

import com.taehee.wordcard.data.model.GithubRepoRes
import com.taehee.wordcard.data.service.GithubService
import javax.inject.Inject

interface GithubRemoteSource {
    suspend fun getRepos(owner: String): List<GithubRepoRes>
}

class GithubRemoteSourceImpl @Inject constructor(
    private val githubService: GithubService,
) : GithubRemoteSource {
    override suspend fun getRepos(owner: String): List<GithubRepoRes> {
        return githubService.getRepos(owner)
    }
}