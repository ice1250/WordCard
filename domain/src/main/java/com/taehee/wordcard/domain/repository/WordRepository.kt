package com.taehee.wordcard.domain.repository

import com.taehee.wordcard.domain.model.Card
import com.taehee.wordcard.domain.model.Game
import com.taehee.wordcard.domain.model.Word
import kotlinx.coroutines.flow.Flow

interface WordRepository {

    fun getWords(): Flow<List<Word>>

    suspend fun getGame(): List<Game>

    suspend fun addWord(word: Word)

    suspend fun removeWord(word: Word)

    fun getCard(): Flow<Card>

}