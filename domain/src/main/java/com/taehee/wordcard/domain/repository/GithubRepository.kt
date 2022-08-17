package com.taehee.wordcard.domain.repository

import com.taehee.wordcard.domain.model.GithubRepo
import kotlinx.coroutines.flow.Flow

interface GithubRepository {

    fun getRepos(owner: String): Flow<List<GithubRepo>>
}