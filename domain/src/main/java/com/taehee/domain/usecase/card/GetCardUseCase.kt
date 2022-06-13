package com.taehee.domain.usecase.card

import com.taehee.domain.model.Card
import com.taehee.domain.repository.CardRepository
import kotlinx.coroutines.*

class GetCardUseCase(private val repository: CardRepository) {

    operator fun invoke(
        text: String?,
        delayTime: Long,
        scope: CoroutineScope,
        onResult: (Card?) -> Unit = {},
    ) {
        scope.launch(Dispatchers.Main) {
            val deferred = async(Dispatchers.IO) {
                delay(delayTime)
                repository.getCard(text)
            }
            onResult(deferred.await())
        }
    }
}