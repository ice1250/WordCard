package com.taehee.yuencard.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.taehee.data.database.WordDao
import com.taehee.data.database.WordsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): WordsDatabase {
        return Room.databaseBuilder(
            appContext,
            WordsDatabase::class.java,
            "words.db"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
//                db.execSQL("INSERT into word (name, time) values ('소연', 0)")
            }
        }).build()
    }

    @Provides
    fun provideWordDao(database: WordsDatabase): WordDao {
        return database.wordDao()
    }
}