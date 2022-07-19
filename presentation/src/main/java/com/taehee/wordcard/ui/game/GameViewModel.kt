package com.taehee.wordcard.ui.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taehee.domain.model.Game
import com.taehee.domain.usecase.word.GetGameUseCase
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

    val score = MutableLiveData(0)

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    init {
        fetchGame()
    }

    fun fetchGame() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update {
                it.copy(score = 0, isClickable = false, isGameWin = false)
            }
            val gameList: List<Game> = withContext(Dispatchers.IO) {
                getGameUseCase()
            }
            _uiState.update {
                it.copy(isClickable = true, gameList = gameList)
            }
        }
    }

    fun reGame() {
        fetchGame()
    }

    private fun checkComplete() {
        if (_uiState.value.gameList.size == _uiState.value.gameList.filter {
                it.state == Game.GameState.SUCCESS
            }.size) {
            _uiState.update {
                it.copy(isGameWin = true)
            }
        }
    }

    fun select(game: Game) {
        viewModelScope.launch {
            if (game.state == Game.GameState.NONE && _uiState.value.isClickable) {
                _uiState.update {
                    it.copy(isClickable = false)
                }

                _uiState.value.gameList.update(Game(game.name, game.num, Game.GameState.FLIP))
                    .also { games ->
                        _uiState.update {
                            it.copy(gameList = games)
                        }
                    }


                val flipList = _uiState.value.gameList.flipList()
                if (flipList.size == 2) {
                    val state = if (flipList.isSame()) {
                        delay(100)
                        score.value = score.value?.plus(10)
                        Game.GameState.SUCCESS
                    } else {
                        delay(1000)
                        score.value = score.value?.minus(10)
                        Game.GameState.NONE
                    }
                    flipList.map {
                        _uiState.value.gameList.update(Game(it.name, it.num, state))
                            .also { update ->
                                _uiState.update {
                                    it.copy(gameList = update)
                                }
                            }
                    }
                    checkComplete()
                    _uiState.update {
                        it.copy(isClickable = true)
                    }
                } else {
                    delay(100)
                    _uiState.update {
                        it.copy(isClickable = true)
                    }
                }
            }
        }
    }
}