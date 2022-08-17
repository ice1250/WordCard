package com.taehee.wordcard.device.repository

import android.speech.tts.TextToSpeech
import com.taehee.wordcard.domain.repository.TtsRepository
import javax.inject.Inject

class TtsRepositoryImpl @Inject constructor(private val tts: TextToSpeech) :
    TtsRepository {
    override fun speak(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun stop() {
        tts.stop()
        tts.shutdown()
    }
}