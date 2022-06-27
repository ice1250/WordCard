package com.taehee.wordcard.ui.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taehee.domain.model.Card
import com.taehee.domain.usecase.word.GetCardUseCase
import com.taehee.domain.usecase.tts.SpeakTtsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val speakTtsUseCase: SpeakTtsUseCase,
    private val getCardUseCase: GetCardUseCase,
) : ViewModel() {

    private val _completeLoading = MutableLiveData<Boolean>()
    val completeLoading: LiveData<Boolean> get() = _completeLoading

    val card: MutableLiveData<Card> = MutableLiveData<Card>().apply {
        getCard(null)
    }

    fun speak(text: String) {
        speakTtsUseCase(text)
    }

    fun getCard(text: String?) {
        _completeLoading.value = false
        getCardUseCase(text, viewModelScope) {
            card.value = it
            _completeLoading.value = true
        }
    }
}