package com.taehee.wordcard.data.repository

import com.taehee.wordcard.data.source.GithubRemoteSource
import com.taehee.wordcard.domain.model.GithubRepo
import com.taehee.wordcard.domain.repository.GithubRepository
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