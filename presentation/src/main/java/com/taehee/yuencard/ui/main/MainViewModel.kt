package com.taehee.yuencard.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.taehee.domain.usecase.SpeakTtsUseCase
import com.taehee.domain.usecase.StopTtsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val speakTtsUseCase: SpeakTtsUseCase,
    private val stopTtsUseCase: StopTtsUseCase
) : ViewModel() {

    val needRefresh = MutableLiveData<Boolean>().apply {
        postValue(true)
    }

    fun dataChanged() {
        needRefresh.value = true
    }

    fun clear() {
        needRefresh.value = false
    }

    fun speakTts(text: String) {
        speakTtsUseCase(text)
    }

    fun stopTts() {
        stopTtsUseCase()
    }
}