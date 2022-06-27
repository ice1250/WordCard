package com.taehee.wordcard.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.taehee.domain.model.Word
import com.taehee.domain.usecase.word.GetWordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    getWordUseCase: GetWordsUseCase,
) : ViewModel() {
    val words: LiveData<List<Word>> = getWordUseCase().asLiveData()
}