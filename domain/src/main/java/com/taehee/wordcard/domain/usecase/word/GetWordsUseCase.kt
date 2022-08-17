package com.taehee.wordcard.domain.usecase.word

import com.taehee.wordcard.domain.repository.WordRepository

class GetWordsUseCase(private val wordRepository: WordRepository) {
    operator fun invoke() = wordRepository.getWords()
}