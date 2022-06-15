package com.taehee.data.source

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
        "#ffc0cb",
        "#ffb6c1",
        "#db7093",
        "#ff1493",
        "#ff69b4",
    )