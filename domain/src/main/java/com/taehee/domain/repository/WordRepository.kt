package com.taehee.domain.repository

import com.taehee.domain.model.Card
import com.taehee.domain.model.Word
import kotlinx.coroutines.flow.Flow

interface WordRepository {

    fun getWords(): Flow<List<Word>>

    suspend fun addWord(word: Word)

    suspend fun removeWord(word: Word)

    suspend fun getCard(name: String?): Card?
}