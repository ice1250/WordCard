package com.taehee.data.repository

import com.taehee.data.mapper.WordMapper
import com.taehee.data.source.WordDataSource
import com.taehee.domain.model.Card
import com.taehee.domain.model.Game
import com.taehee.domain.model.Word
import com.taehee.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(
    private val dataSource: WordDataSource,
) : WordRepository {

    override fun getWords(): Flow<List<Word>> {
        return dataSource.getWords().map { WordMapper.toWords(it) }
    }

    override suspend fun getGame(): List<Game> {
        return dataSource.getGames().let { WordMapper.toGames(it) }
    }

    override suspend fun addWord(word: Word) = dataSource.addWord(WordMapper.toWordEntity(word))

    override suspend fun removeWord(word: Word) =
        dataSource.removeWord(WordMapper.toWordEntity(word))

    override suspend fun getCard(name: String?): Card? {
        return dataSource.getRandomWord(name)?.let { WordMapper.toCard(it) }
    }
}