package com.taehee.wordcard.ui.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taehee.domain.model.GithubRepo
import com.taehee.domain.usecase.github.GetGithubReposUseCase
import com.taehee.wordcard.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val getGithubReposUseCase: GetGithubReposUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<GithubRepo>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<GithubRepo>>> = _uiState

    private var fetchJob: Job? = null

    fun getGithubRepositories(owner: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            getGithubReposUseCase(owner).collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }
}