package com.taehee.wordcard.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.taehee.domain.usecase.tts.StopTtsUseCase
import com.taehee.wordcard.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val stopTtsUseCase: StopTtsUseCase,
) : ViewModel() {

    private val _completeInit = MutableLiveData<Boolean>()
    val completeInit: LiveData<Boolean> get() = _completeInit

    private val _wordChanged = MutableLiveData<Event<Boolean>>()
    val wordChanged: LiveData<Event<Boolean>> = _wordChanged
    fun wordChange() {
        _wordChanged.value = Event(true)
    }

    init {
        _completeInit.value = false
//        viewModelScope.launch {
//            delay(2000)
//            _completeInit.value = true
//        }
    }

    fun initComplete() {
        _completeInit.value = true
    }

    override fun onCleared() {
        stopTtsUseCase()
        super.onCleared()
    }
}