package com.taehee.wordcard.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taehee.domain.model.Word
import com.taehee.domain.usecase.word.AddWordUseCase
import com.taehee.domain.usecase.word.GetWordsUseCase
import com.taehee.domain.usecase.word.RemoveWordUseCase
import com.taehee.wordcard.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    getWordUseCase: GetWordsUseCase,
    private val addWordUseCase: AddWordUseCase,
    private val removeWordUseCase: RemoveWordUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Word>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Word>>> = _uiState

    init {
        viewModelScope.launch {
            getWordUseCase().collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }

    fun addWord(text: String) {
        if (text.isNotEmpty())
            addWordUseCase(Word(text, System.currentTimeMillis()), viewModelScope)
    }

    fun deleteWord(word: Word) {
        removeWordUseCase(word, viewModelScope)
    }
}