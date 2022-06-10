package com.taehee.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.taehee.data.model.WordEntity

@Database(entities = [WordEntity::class], version = 1, exportSchema = false)
abstract class WordsDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}