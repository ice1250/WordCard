package com.taehee.data.mapper

import com.taehee.data.model.WordEntity
import com.taehee.domain.model.Word

fun mapperToWord(wordEntities: List<WordEntity>): List<Word> {
    return wordEntities.map { it.map() }
}

fun WordEntity.map(): Word {
    return Word(name, time).apply { color = this@map.color }
}

fun mapperToWordEntity(words: List<Word>): List<WordEntity> {
    return words.map { it.map() }
}

fun Word.map() = WordEntity(name, time)
