package com.taehee.wordcard.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taehee.domain.model.Word
import com.taehee.domain.usecase.tts.SpeakTtsUseCase
import com.taehee.domain.usecase.tts.StopTtsUseCase
import com.taehee.domain.usecase.word.GetRandomWordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val speakTtsUseCase: SpeakTtsUseCase,
    private val stopTtsUseCase: StopTtsUseCase,
    private val getRandomWordUseCase: GetRandomWordUseCase,
) : ViewModel() {

    var isReady: Boolean = false
    fun speakTts(text: String, isRefresh: Boolean = false) {
        when {
            isRefresh -> when {
                !isLoading -> {
                    isLoading = true
                    speakTtsUseCase(text)
                    refreshCard()
                }
            }
            else -> speakTtsUseCase(text)
        }
    }

    fun stopTts() = stopTtsUseCase()

    val card: MutableLiveData<Word> by lazy {
        MutableLiveData<Word>().also {
            getRandomWordUseCase(scope = viewModelScope) {
                card.value = it
            }
        }
    }

    private var isLoading = false

    fun refreshCard() {
        getRandomWordUseCase(
            card.value?.name, true, viewModelScope
        ) {
            card.value = it
            isLoading = false
        }
    }

    fun init() {
        viewModelScope.launch {
            delay(2000)
            isReady = true
        }
    }

}