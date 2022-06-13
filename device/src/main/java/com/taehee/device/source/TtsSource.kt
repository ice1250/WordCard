package com.taehee.device.source

interface TtsSource {

    fun speak(text: String)
    fun stop()
}