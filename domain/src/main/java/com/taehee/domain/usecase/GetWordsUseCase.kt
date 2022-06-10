package com.taehee.domain.usecase

import com.taehee.domain.repository.WordRepository

class GetWordsUseCase(private val wordRepository: WordRepository) {
    operator fun invoke() = wordRepository.getWords()
}