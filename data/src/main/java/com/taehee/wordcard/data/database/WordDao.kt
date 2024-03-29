package com.taehee.wordcard.data.database

import androidx.room.*
import com.taehee.wordcard.data.model.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Query("SELECT * FROM word")
    fun getWords(): Flow<List<WordEntity>>

    @Query("SELECT * FROM word ORDER BY RANDOM() LIMIT 14")
    fun getGames(): List<WordEntity>

//    @Query("SELECT * FROM word WHERE (name!=:name) OR :name IS NULL ORDER BY RANDOM() LIMIT 1")
//    fun getRandomWord(name: String?): WordEntity?

    @Query("SELECT * FROM word ORDER BY RANDOM() LIMIT 1")
    fun getRandomWord(): Flow<WordEntity?>

    @Query("SELECT COUNT(*) FROM word")
    fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addWord(word: WordEntity)

    @Delete
    fun removeWord(word: WordEntity)
}