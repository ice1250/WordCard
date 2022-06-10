package com.taehee.data.database

import androidx.room.*
import com.taehee.data.model.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Query("SELECT * FROM word")
    fun getWords(): Flow<List<WordEntity>>

    @Query("SELECT * FROM word WHERE (name!=:name) ORDER BY RANDOM() LIMIT 1")
    fun getRandomWord(name: String?): WordEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addWord(word: WordEntity)

    @Delete
    fun removeWord(word: WordEntity)
}