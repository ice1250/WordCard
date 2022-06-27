package com.taehee.wordcard.ui.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taehee.domain.model.Word
import com.taehee.domain.usecase.tts.SpeakTtsUseCase
import com.taehee.domain.usecase.word.GetRandomWordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val speakTtsUseCase: SpeakTtsUseCase,
    private val getRandomWordUseCase: GetRandomWordUseCase,
) : ViewModel() {

    private val _completeLoading = MutableLiveData<Boolean>()
    val completeLoading: LiveData<Boolean> get() = _completeLoading

    val card: MutableLiveData<Word> = MutableLiveData<Word>().apply {
        getCard(null)
    }

    fun speak(text: String) {
        speakTtsUseCase(text)
    }

    fun getCard(text: String?) {
        _completeLoading.value = false
        getRandomWordUseCase(text, viewModelScope) {
            card.value = it
            _completeLoading.value = true
        }
    }
}