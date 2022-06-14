package com.taehee.domain.usecase.word

import com.taehee.domain.model.Word
import com.taehee.domain.repository.WordRepository
import kotlinx.coroutines.*

class GetRandomWordUseCase(private val repository: WordRepository) {

    operator fun invoke(
        scope: CoroutineScope,
        onResult: (Word?) -> Unit = {},
    ) {
        invoke(null, scope, onResult)
    }

    operator fun invoke(
        text: String?,
        scope: CoroutineScope,
        onResult: (Word?) -> Unit = {},
    ) {
        scope.launch(Dispatchers.Main) {
            val deferred = async(Dispatchers.IO) {
                delay(1000)
                repository.getRandomWord(text)
            }
            onResult(deferred.await())
        }
    }
}