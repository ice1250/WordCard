package com.taehee.domain.usecase.word

import com.taehee.domain.model.Card
import com.taehee.domain.repository.WordRepository

class GetCardUseCase(private val repository: WordRepository) {
    suspend operator fun invoke(
        text: String? = null,
    ): Card? {
        return repository.getCard(text)
    }
}