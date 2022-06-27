package com.taehee.domain.usecase.word

import com.taehee.domain.model.Word
import com.taehee.domain.repository.WordRepository
import kotlinx.coroutines.*

class GetRandomWordUseCase(private val repository: WordRepository) {

    operator fun invoke(
        text: String? = null,
        scope: CoroutineScope,
        onResult: (Word?) -> Unit = {},
    ) {
        scope.launch(Dispatchers.Main) {
            onResult(withContext(Dispatchers.IO) {
                delay(1000)
                repository.getRandomWord(text)
            })
        }
    }
}