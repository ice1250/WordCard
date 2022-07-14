package com.taehee.data.repository

import com.taehee.data.source.GithubRemoteSource
import com.taehee.domain.model.GithubRepo
import com.taehee.domain.repository.GithubRepository
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubRemoteSource: GithubRemoteSource,
) : GithubRepository {
    override suspend fun getRepos(owner: String): List<GithubRepo> {
        return githubRemoteSource.getRepos(owner)
    }
}