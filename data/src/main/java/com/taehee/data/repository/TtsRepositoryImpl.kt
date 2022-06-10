package com.taehee.data.repository

import com.taehee.data.datasource.TtsDataSource
import com.taehee.domain.repository.TtsRepository
import javax.inject.Inject

class TtsRepositoryImpl @Inject constructor(private val dataSource: TtsDataSource) :
    TtsRepository {
    override fun speak(text: String) {
        dataSource.speak(text)
    }

    override fun stop() {
        dataSource.stop()
    }
}