package com.taehee.yuencard.ui.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taehee.domain.model.Card
import com.taehee.domain.usecase.card.GetCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val getCardUseCase: GetCardUseCase
) : ViewModel() {

    private val _card = MutableLiveData<Card>()
    val card: LiveData<Card> = _card

    fun getCard(text: String?, delayTime: Long) {
        getCardUseCase(text, delayTime, viewModelScope) {
            _card.value = it
        }
    }
}