package com.taehee.data.repository

import com.taehee.data.datasource.WordDataSource
import com.taehee.data.mapper.map
import com.taehee.data.mapper.mapperToWord
import com.taehee.domain.model.Word
import com.taehee.domain.repository.WordRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(
    private val dataSource: WordDataSource,
) : WordRepository {

    override fun getWords() =
        dataSource.getWords().map { mapperToWord(it) }

    override suspend fun addWord(word: Word) {
        dataSource.addWord(word.map())
    }

    override suspend fun removeWord(word: Word) {
        dataSource.removeWord(word.map())
    }
}