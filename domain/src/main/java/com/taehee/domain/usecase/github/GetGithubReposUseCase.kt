package com.taehee.domain.usecase.github

import com.taehee.domain.model.GithubRepo
import com.taehee.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow

class GetGithubReposUseCase(private val githubRepository: GithubRepository) {

    operator fun invoke(
        owner: String,
    ): Flow<List<GithubRepo>> {
        return githubRepository.getRepos(owner)
    }
}