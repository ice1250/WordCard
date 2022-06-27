package com.taehee.wordcard.ui.card

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taehee.domain.model.Card
import com.taehee.domain.usecase.tts.SpeakTtsUseCase
import com.taehee.domain.usecase.word.GetCardUseCase
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
        getCard()
    }

    fun speak(text: String) {
        speakTtsUseCase(text)
    }

    fun getCard(text: String? = null) {
        _completeLoading.value = false
        getCardUseCase(text, viewModelScope) {
            card.value = it
            _completeLoading.value = true
        }
    }
}