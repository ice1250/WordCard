package com.taehee.domain.usecase

import com.taehee.domain.model.Word
import com.taehee.domain.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RemoveWordUseCase(private val wordRepository: WordRepository) {
    operator fun invoke(
        word: Word,
        scope: CoroutineScope,
    ) {
        scope.launch(Dispatchers.IO) {
            wordRepository.removeWord(word)
        }
    }

}