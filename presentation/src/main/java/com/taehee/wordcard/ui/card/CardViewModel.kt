package com.taehee.wordcard.ui.card

import android.view.MotionEvent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taehee.domain.model.Card
import com.taehee.domain.repository.TtsRepository
import com.taehee.domain.usecase.word.GetCardUseCase
import com.taehee.wordcard.ui.base.UiState
import com.taehee.wordcard.ui.base.successOrNull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val getCardUseCase: GetCardUseCase,
    private val ttsRepository: TtsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Card>>(UiState.Loading)
    val uiState: StateFlow<UiState<Card>> = _uiState

    private val _motionEventLiveData = MutableLiveData<MotionEvent>()
    val motionEventLiveData = _motionEventLiveData

    private var fetchJob: Job? = null

    init {
        fetchCard()
    }

    fun fetchCard(isNeedDelay: Boolean = false) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.value = UiState.Loading
            if (isNeedDelay) {
                delay(1000)
            }
            getCardUseCase().collect {
                _uiState.value =
                    if (it.name.isNotEmpty()) UiState.Success(it) else UiState.Error(
                        Throwable())
            }
        }
    }

    fun onClickedCard(uiState: UiState<Card>) {
        uiState.successOrNull()?.apply {
            ttsRepository.speak(name)
            fetchCard(true)
        }
    }

    fun onTouchedCard(motionEvent: MotionEvent) {
        viewModelScope.launch {
            _motionEventLiveData.value = motionEvent
        }
    }

    override fun onCleared() {
        ttsRepository.stop()
        super.onCleared()
    }
}