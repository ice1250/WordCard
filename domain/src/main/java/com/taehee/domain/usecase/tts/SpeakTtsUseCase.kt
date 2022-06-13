package com.taehee.domain.usecase.tts

import com.taehee.domain.repository.TtsRepository

class SpeakTtsUseCase(private val ttsRepository: TtsRepository) {
    operator fun invoke(text: String) = ttsRepository.speak(text)
}