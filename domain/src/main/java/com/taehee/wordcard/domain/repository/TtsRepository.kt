package com.taehee.wordcard.domain.repository

interface TtsRepository {

    fun speak(text: String)
    fun stop()
}