package com.taehee.domain.usecase

import com.taehee.domain.repository.TtsRepository

class StopTtsUseCase(private val ttsRepository: TtsRepository) {
    operator fun invoke() = ttsRepository.stop()
}