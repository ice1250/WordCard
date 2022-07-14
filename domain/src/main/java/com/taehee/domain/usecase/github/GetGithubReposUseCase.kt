package com.taehee.domain.usecase.github

import com.taehee.domain.model.GithubRepo
import com.taehee.domain.repository.GithubRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class GetGithubReposUseCase(private val githubRepository: GithubRepository) {

    operator fun invoke(
        owner: String,
        scope: CoroutineScope,
        onResult: (List<GithubRepo>) -> Unit = {},
    ) {
        scope.launch(Dispatchers.Main) {
            val deferred = async(Dispatchers.IO) {
                githubRepository.getRepos(owner)
            }
            onResult(deferred.await())
        }
    }
}