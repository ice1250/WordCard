package com.taehee.domain.usecase.word

import com.taehee.domain.repository.WordRepository

class GetGameUseCase(private val repository: WordRepository) {

    operator fun invoke() = repository.getGame()
}