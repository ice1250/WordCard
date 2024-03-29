package com.taehee.wordcard.presentation.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taehee.wordcard.domain.model.Game
import com.taehee.wordcard.domain.usecase.word.GetGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val getGameUseCase: GetGameUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    private var isClickable = false

    init {
        fetchGame()
    }

    private fun fetchGame() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update {
                it.copy(score = 0, isGameWin = false)
            }
            val gameList: List<Game> = withContext(Dispatchers.IO) {
                getGameUseCase()
            }
            _uiState.update {
                it.copy(gameList = gameList)
            }
            isClickable = true
        }
    }

    fun reGame() {
        fetchGame()
    }

    fun select(game: Game) {

        if (game.state == Game.GameState.NONE && isClickable) {
            isClickable = false
            fetchJob?.cancel()
            fetchJob = viewModelScope.launch {

                _uiState.value.gameList.update(Game(game.name, game.num, Game.GameState.FLIP))
                    .also { games ->
                        _uiState.update {
                            it.copy(gameList = games)
                        }
                    }


                val flipList = _uiState.value.gameList.flipList()
                if (flipList.size == 2) {
                    delay(1000)
                    val state = if (flipList.isSame()) {
                        _uiState.update {
                            it.copy(score = _uiState.value.score.plus(10))
                        }
                        Game.GameState.SUCCESS
                    } else {
                        _uiState.update {
                            it.copy(score = _uiState.value.score.minus(10))
                        }
                        Game.GameState.NONE
                    }
                    flipList.map { game ->
                        _uiState.value.gameList.update(Game(game.name, game.num, state))
                            .also { update ->
                                _uiState.update {
                                    it.copy(gameList = update)
                                }
                            }
                    }
                    if (_uiState.value.gameList.size == _uiState.value.gameList.filter {
                            it.state == Game.GameState.SUCCESS
                        }.size) {
                        _uiState.update {
                            it.copy(isGameWin = true)
                        }
                    }
                    isClickable = true
                } else {
                    delay(500)
                    isClickable = true
                }
            }
        }
    }
}