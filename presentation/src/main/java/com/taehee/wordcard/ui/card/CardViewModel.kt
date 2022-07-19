package com.taehee.wordcard.ui.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taehee.domain.model.Card
import com.taehee.domain.usecase.word.GetCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val getCardUseCase: GetCardUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CardUiState())
    val uiState: StateFlow<CardUiState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    init {
        fetchCard()
    }

    fun fetchCard(text: String? = null, isNeedDelay: Boolean = true) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update {
                it.copy(isFetchingCard = true, errorMessage = "")
            }
            val card: Card? = withContext(Dispatchers.IO) {
                if (isNeedDelay) {
                    delay(1000)
                }
                getCardUseCase(text)
            }
            _uiState.update {
                if (card != null) {
                    it.copy(isFetchingCard = false, errorMessage = "", card = card)
                } else {
                    it.copy(isFetchingCard = false, errorMessage = "카드를 등록 해 주세요", card = null)
                }
            }
        }
    }
}