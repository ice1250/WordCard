package com.taehee.domain.usecase.word

import com.taehee.domain.model.Card
import com.taehee.domain.repository.WordRepository
import kotlinx.coroutines.*

class GetCardUseCase(private val repository: WordRepository) {

    operator fun invoke(
        text: String? = null,
        scope: CoroutineScope,
        isNeedDelay: Boolean = true,
        onResult: (Card?) -> Unit = {},
    ) {
        scope.launch(Dispatchers.Main) {
            onResult(withContext(Dispatchers.IO) {
                if (isNeedDelay) {
                    delay(1000)
                }
                repository.getCard(text)
            })
        }
    }

    suspend operator fun invoke(
        text: String? = null,
    ): Card? {
        return repository.getCard(text)
    }
}