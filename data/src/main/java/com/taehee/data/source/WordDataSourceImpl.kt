package com.taehee.data.source

import android.graphics.Color
import com.taehee.data.database.WordDao
import com.taehee.data.model.WordEntity
import java.security.SecureRandom
import javax.inject.Inject

class WordDataSourceImpl @Inject constructor(private val wordDao: WordDao) : WordDataSource {

    override fun getWords() = wordDao.getWords()

    override fun getRandomWord(name: String?): WordEntity? = wordDao.getRandomWord(
        if (wordDao.getCount() >= 2) name
        else null
    )?.apply { color = colors[SecureRandom.getInstanceStrong().nextInt((colors.size))] }

    override fun addWord(word: WordEntity) = wordDao.addWord(word)

    override fun removeWord(word: WordEntity) = wordDao.removeWord(word)
}

private val colors =
    mutableListOf(
        Color.parseColor("#ffc0cb"),
        Color.parseColor("#ffb6c1"),
        Color.parseColor("#db7093"),
        Color.parseColor("#ff1493"),
        Color.parseColor("#ff69b4"),
    )