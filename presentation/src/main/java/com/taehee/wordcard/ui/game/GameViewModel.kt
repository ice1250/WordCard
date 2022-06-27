package com.taehee.wordcard.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.taehee.domain.model.Card
import com.taehee.domain.model.Game
import com.taehee.domain.model.Word
import com.taehee.domain.usecase.tts.SpeakTtsUseCase
import com.taehee.domain.usecase.word.GetGameUseCase
import com.taehee.domain.usecase.word.GetWordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val speakTtsUseCase: SpeakTtsUseCase,
    getGameUseCase: GetGameUseCase
) : ViewModel() {
    val items: LiveData<List<Game>> = getGameUseCase().asLiveData()

    fun speak(text: String) {

    }
}