package com.taehee.wordcard.ui.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taehee.domain.model.Card
import com.taehee.domain.usecase.word.GetCardUseCase
import com.taehee.wordcard.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val getCardUseCase: GetCardUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Card>>(UiState.Loading)
    val uiState: StateFlow<UiState<Card>> = _uiState

    private var fetchJob: Job? = null

    init {
        fetchCard()
    }

    fun fetchCard(text: String? = null, isNeedDelay: Boolean = true) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.value = UiState.Loading

            val card: Card? = withContext(Dispatchers.IO) {
                if (isNeedDelay) {
                    delay(1000)
                }
                getCardUseCase(text)
            }
            if (card != null) {
                _uiState.value = UiState.Success(card)
            } else {
                _uiState.value = UiState.Error(Throwable())
            }
        }
    }
}