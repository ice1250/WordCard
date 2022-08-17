package com.taehee.wordcard.domain.usecase.github

import com.taehee.wordcard.domain.model.GithubRepo
import com.taehee.wordcard.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow

class GetGithubReposUseCase(private val githubRepository: GithubRepository) {

    operator fun invoke(
        owner: String,
    ): Flow<List<GithubRepo>> {
        return githubRepository.getRepos(owner)
    }
}