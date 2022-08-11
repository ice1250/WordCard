package com.taehee.device.source

import android.speech.tts.TextToSpeech
import javax.inject.Inject

class TtsSourceImpl @Inject constructor(private val tts: TextToSpeech) : TtsSource {
    override fun speak(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun stop() {
        tts.stop()
        tts.shutdown()
    }
}