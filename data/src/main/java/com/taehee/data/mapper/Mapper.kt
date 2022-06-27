package com.taehee.data.mapper

import android.graphics.Color
import com.taehee.data.model.WordEntity
import com.taehee.domain.model.Card
import com.taehee.domain.model.Word
import java.security.SecureRandom

fun mapperToWord(wordEntities: List<WordEntity>): List<Word> {
    return wordEntities.map { it.toWord() }
}

fun WordEntity.toWord(): Word {
    return Word(name, time)
}

fun WordEntity.toCard(): Card {
    return Card(name, time, colors[SecureRandom.getInstanceStrong().nextInt((colors.size))])
}

fun mapperToWordEntity(words: List<Word>): List<WordEntity> {
    return words.map { it.map() }
}

fun Word.map() = WordEntity(name, time)

private val colors =
    mutableListOf(
        Color.parseColor("#ffc0cb"),
        Color.parseColor("#ffb6c1"),
        Color.parseColor("#db7093"),
        Color.parseColor("#ff1493"),
        Color.parseColor("#ff69b4"),
    )