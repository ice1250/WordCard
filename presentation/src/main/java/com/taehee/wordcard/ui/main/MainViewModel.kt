package com.taehee.wordcard.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taehee.domain.usecase.tts.SpeakTtsUseCase
import com.taehee.domain.usecase.tts.StopTtsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val stopTtsUseCase: StopTtsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun wordChanged() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(needRefreshCard = true, needRefreshGame = true)
            }
        }
    }

    fun refreshFinished(card: Boolean = false, game: Boolean = false) {
        viewModelScope.launch {
            when {
                card -> {
                    _uiState.update {
                        it.copy(needRefreshCard = false)
                    }
                }
                game -> {
                    _uiState.update {
                        it.copy(needRefreshGame = false)
                    }
                }
            }
        }
    }

    override fun onCleared() {
        stopTtsUseCase()
        super.onCleared()
    }
}