package com.taehee.data.repository

import com.taehee.data.mapper.map
import com.taehee.data.mapper.mapperToWord
import com.taehee.data.mapper.toCard
import com.taehee.data.mapper.toGame
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

    override fun getWords() =
        dataSource.getWords().map { mapperToWord(it) }

    override fun getGame(): Flow<List<Game>> {
        return dataSource.getWords().map { it.map { wordEntity -> wordEntity.toGame() } }
    }

    override suspend fun addWord(word: Word) = dataSource.addWord(word.map())

    override suspend fun removeWord(word: Word) = dataSource.removeWord(word.map())

    override suspend fun getCard(name: String?): Card? {
        return dataSource.getRandomWord(name)?.toCard()
    }

}