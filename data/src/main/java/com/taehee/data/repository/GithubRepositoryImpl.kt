package com.taehee.data.repository

import com.taehee.data.source.GithubRemoteSource
import com.taehee.domain.model.GithubRepo
import com.taehee.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubRemoteSource: GithubRemoteSource,
) : GithubRepository {

    override fun getRepos(owner: String): Flow<List<GithubRepo>> {
        return flow {
            val items = githubRemoteSource.getRepos(owner)
            emit(items)
        }
    }
}