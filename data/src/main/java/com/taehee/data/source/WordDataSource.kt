package com.taehee.data.source

import com.taehee.data.model.WordEntity
import kotlinx.coroutines.flow.Flow

interface WordDataSource {
    fun getWords(): Flow<List<WordEntity>>
    fun getRandomWord(name: String?): WordEntity?
    fun addWord(word: WordEntity)
    fun removeWord(word: WordEntity)
}