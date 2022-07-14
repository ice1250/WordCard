package com.taehee.domain.repository

import com.taehee.domain.model.GithubRepo

interface GithubRepository {

    suspend fun getRepos(owner: String): List<GithubRepo>
}