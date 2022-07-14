package com.taehee.wordcard.ui.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taehee.domain.model.GithubRepo
import com.taehee.domain.usecase.github.GetGithubReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val getGithubReposUseCase: GetGithubReposUseCase,
) : ViewModel() {

    private val _githubRepositories = MutableLiveData<List<GithubRepo>>()
    val githubRepositories: LiveData<List<GithubRepo>> = _githubRepositories

    fun getGithubRepositories(owner: String) {
        getGithubReposUseCase(owner, viewModelScope) {
            _githubRepositories.value = it
        }
    }
}