package com.taehee.data.datasource

interface TtsDataSource {

    fun speak(text: String)
    fun stop()
}