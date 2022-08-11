package com.taehee.data.source

import com.taehee.data.database.WordDao
import com.taehee.data.model.WordEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface WordDataSource {
    fun getWords(): Flow<List<WordEntity>>
    fun getGames(): List<WordEntity>
    fun getRandomWord(): Flow<WordEntity?>
    fun addWord(word: WordEntity)
    fun removeWord(word: WordEntity)
}

class WordDataSourceImpl @Inject constructor(private val wordDao: WordDao) : WordDataSource {

    override fun getWords() = wordDao.getWords()

    override fun getGames(): List<WordEntity> {
        return wordDao.getGames()
    }

    override fun getRandomWord() = wordDao.getRandomWord()

    override fun addWord(word: WordEntity) = wordDao.addWord(word)

    override fun removeWord(word: WordEntity) = wordDao.removeWord(word)

}