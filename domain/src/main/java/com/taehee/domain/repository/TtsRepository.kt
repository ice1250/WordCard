package com.taehee.domain.repository

interface TtsRepository {

    fun speak(text: String)
    fun stop()
}