package com.taehee.data.source

import com.taehee.data.database.WordDao
import com.taehee.domain.model.Card
import java.security.SecureRandom
import javax.inject.Inject

class CardDataSourceImpl @Inject constructor(private val wordDao: WordDao) : CardDataSource {

    override fun getCard(name: String?): Card? {

        wordDao.getRandomWord(name).also {
            return if (it == null) null else Card(
                it.name,
                colors[SecureRandom.getInstanceStrong().nextInt((colors.size))]
            )
        }
    }
}

private val colors =
    mutableListOf(
        "#ffc0cb",
        "#ffb6c1",
        "#db7093",
        "#ff1493",
        "#ff69b4",
    )
