package com.taehee.domain.usecase.tts

import com.taehee.domain.repository.TtsRepository

class StopTtsUseCase(private val ttsRepository: TtsRepository) {
    operator fun invoke() = ttsRepository.stop()
}