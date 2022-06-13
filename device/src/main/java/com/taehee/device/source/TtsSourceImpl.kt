package com.taehee.device.source

import android.speech.tts.TextToSpeech
import javax.inject.Inject

class TtsSourceImpl @Inject constructor(private val textToSpeech: TextToSpeech): TtsSource {
    override fun speak(text: String) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun stop() {
        textToSpeech.stop()
        textToSpeech.shutdown()
    }
}