package com.taehee.yuencard.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taehee.domain.model.Word
import com.taehee.domain.usecase.tts.SpeakTtsUseCase
import com.taehee.domain.usecase.tts.StopTtsUseCase
import com.taehee.domain.usecase.word.GetRandomWordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val speakTtsUseCase: SpeakTtsUseCase,
    private val stopTtsUseCase: StopTtsUseCase,
    private val getRandomWordUseCase: GetRandomWordUseCase,
) : ViewModel() {

    fun speakTts(text: String) {
        speakTtsUseCase(text)
    }

    fun stopTts() {
        stopTtsUseCase()
    }

    val card: MutableLiveData<Word> by lazy {
        MutableLiveData<Word>().also {
            loadCard()
        }
    }

    private fun loadCard() {
        getRandomWordUseCase(viewModelScope) { card.value = it }
    }

    fun refreshCard() {
        getRandomWordUseCase(card.value?.name, viewModelScope) {
            card.value = it
        }
    }

}