package com.taehee.wordcard.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taehee.domain.model.Game
import com.taehee.domain.model.GameState
import com.taehee.domain.usecase.word.GetGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val getGameUseCase: GetGameUseCase,
) : ViewModel() {

    val items: MutableLiveData<List<Game>> = MutableLiveData<List<Game>>().apply {
        loadGames()
    }

    fun loadGames() {
        getGameUseCase(viewModelScope) { items.value = it }
    }

    private val _completeLoading = MutableLiveData<Boolean>()
    val completeLoading: LiveData<Boolean> get() = _completeLoading

    private val _gameComplete = MutableLiveData<Boolean>()
    val gameComplete: LiveData<Boolean> = _gameComplete

    init {
        _completeLoading.value = true
        viewModelScope.launch {
            flow {
                while (true) {
                    delay(1000)
                    emit(items.value!!.isNotEmpty() &&
                            (items.value!!.size == items.value!!.filter {
                                it.state == GameState.SUCCESS
                            }.size))
                }
            }.collect {
                _gameComplete.value = it
            }
        }
    }

    fun select(game: Game) {
        if (game.state == GameState.NONE) {
            _completeLoading.value = false

            items.value!!.update(Game(game.name, game.num, GameState.FLIP)).also {
                items.value = it
            }

            val flipList = items.value!!.flipList()
            viewModelScope.launch {
                if (flipList.size == 2) {
                    val state = if (flipList.isSame()) {
                        GameState.SUCCESS
                    } else {
                        delay(1000)
                        GameState.NONE
                    }
                    flipList.map {
                        items.value!!.update(Game(it.name, it.num, state)).also { update ->
                            items.value = update
                        }
                    }
                }
                _completeLoading.value = true
            }
        }
    }
}