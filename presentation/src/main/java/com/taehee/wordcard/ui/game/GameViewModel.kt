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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val getGameUseCase: GetGameUseCase,
) : ViewModel() {

    val items: MutableLiveData<List<Game>> = MutableLiveData<List<Game>>().apply {
        getGames()
    }

    fun getGames() {
        getGameUseCase(viewModelScope) {
            items.value = it
            checkGameComplete()
        }
    }

    private val _completeLoading = MutableLiveData<Boolean>()
    val completeLoading: LiveData<Boolean> get() = _completeLoading

    private val _gameComplete = MutableLiveData<Boolean>()
    val gameComplete: LiveData<Boolean> = _gameComplete

    init {
        _completeLoading.value = true
    }

    private fun checkGameComplete() {
        _gameComplete.value = false
        viewModelScope.launch {
            while (true) {
                delay(1000)
                val successList = items.value?.filter { it.state != GameState.SUCCESS }
                if (successList.isNullOrEmpty()) {
                    _gameComplete.value = true
                    break
                }
            }
        }
    }

    fun select(game: Game) {
        if (game.state == GameState.NONE) {
            _completeLoading.value = false
            val tempList: MutableList<Game> = mutableListOf()
            items.value?.map {
                if (it.num == game.num) {
                    val newItem = Game(it.name, it.num)
                    newItem.state = GameState.FLIP
                    tempList.add(newItem)
                } else {
                    tempList.add(it)
                }
            }
            items.value = tempList

            val flipList = tempList.filter {
                it.state == GameState.FLIP
            }

            if (flipList.size == 2) {
                val flipTempList: MutableList<Game> = mutableListOf()
                items.value?.map {
                    flipTempList.add(it)
                }
                val firstItem = Game(flipList[0].name, flipList[0].num)
                val secondItem = Game(flipList[1].name, flipList[1].num)
                if (flipList[0].name == flipList[1].name) {
                    firstItem.state = GameState.SUCCESS
                    secondItem.state = GameState.SUCCESS
                    flipTempList[firstItem.num] = firstItem
                    flipTempList[secondItem.num] = secondItem
                    items.value = flipTempList
                    _completeLoading.value = true
                } else {
                    firstItem.state = GameState.NONE
                    secondItem.state = GameState.NONE
                    flipTempList[firstItem.num] = firstItem
                    flipTempList[secondItem.num] = secondItem
                    viewModelScope.launch {
                        delay(1000)
                        items.value = flipTempList
                        _completeLoading.value = true
                    }
                }
            } else {
                _completeLoading.value = true
            }
        }
    }
}