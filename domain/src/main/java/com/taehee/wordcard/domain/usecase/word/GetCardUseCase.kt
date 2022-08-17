package com.taehee.wordcard.domain.usecase.word

import com.taehee.wordcard.domain.repository.WordRepository

class GetCardUseCase(private val repository: WordRepository) {
    operator fun invoke() = repository.getCard()
}