package com.taehee.data.datasource

import android.speech.tts.TextToSpeech
import javax.inject.Inject

class TtsDataSourceImpl @Inject constructor(private val textToSpeech: TextToSpeech): TtsDataSource {
    override fun speak(text: String) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun stop() {
        textToSpeech.stop()
        textToSpeech.shutdown()
    }
}