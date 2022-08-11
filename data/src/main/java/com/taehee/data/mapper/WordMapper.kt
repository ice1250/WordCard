package com.taehee.data.mapper

import android.graphics.Color
import com.taehee.data.model.WordEntity
import com.taehee.domain.model.Card
import com.taehee.domain.model.Game
import com.taehee.domain.model.Word
import java.security.SecureRandom

object WordMapper {
    fun toWords(wordEntities: List<WordEntity>): List<Word> {
        return wordEntities.map { Word(it.name, it.time) }
    }

    fun toCard(wordEntity: WordEntity?): Card {
        val card = Card(wordEntity?.name ?: "")
        card.color = colors[SecureRandom.getInstanceStrong().nextInt((colors.size))]
        return card
    }

    fun toGames(wordEntities: List<WordEntity>): List<Game> {
        val wordList: MutableList<WordEntity> = mutableListOf()
        wordEntities.map {
            wordList.add(it)
            wordList.add(it)
        }
        wordList.shuffle()
        return wordList.mapIndexed { index, wordEntity ->
            Game(wordEntity.name, index, Game.GameState.NONE)
        }
    }

    fun toWordEntity(word: Word): WordEntity {
        return WordEntity(word.name, word.time)
    }

    private val colors =
        mutableListOf(
            Color.parseColor("#ffc0cb"),
            Color.parseColor("#ffb6c1"),
            Color.parseColor("#db7093"),
            Color.parseColor("#ff1493"),
            Color.parseColor("#ff69b4"),
        )
}
