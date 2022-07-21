package com.taehee.domain.repository

import com.taehee.domain.model.GithubRepo
import kotlinx.coroutines.flow.Flow

interface GithubRepository {

    fun getRepos(owner: String): Flow<List<GithubRepo>>
}