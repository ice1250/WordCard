package com.taehee.wordcard.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.taehee.domain.model.Game
import com.taehee.domain.usecase.word.GetGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    getGameUseCase: GetGameUseCase,
) : ViewModel() {
    val items: LiveData<List<Game>> = getGameUseCase().asLiveData()
}