package com.taehee.device.repository

import com.taehee.device.source.TtsSource
import com.taehee.domain.repository.TtsRepository
import javax.inject.Inject

class TtsRepositoryImpl @Inject constructor(private val source: TtsSource) :
    TtsRepository {
    override fun speak(text: String) {
        source.speak(text)
    }

    override fun stop() {
        source.stop()
    }
}