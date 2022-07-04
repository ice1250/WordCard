package com.taehee.data.repository

import com.taehee.data.mapper.WordMapper
import com.taehee.data.source.WordDataSource
import com.taehee.domain.model.Word
import com.taehee.domain.repository.WordRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(
    private val dataSource: WordDataSource,
) : WordRepository {

    override fun getWords() =
        dataSource.getWords().map { WordMapper.toWords(it) }

    override fun getGame() =
        dataSource.getWords().map { WordMapper.toGames(it) }

    override suspend fun addWord(word: Word) = dataSource.addWord(WordMapper.toWordEntity(word))

    override suspend fun removeWord(word: Word) =
        dataSource.removeWord(WordMapper.toWordEntity(word))

    override suspend fun getCard(name: String?) =
        dataSource.getRandomWord(name)?.let { WordMapper.toCard(it) }

}