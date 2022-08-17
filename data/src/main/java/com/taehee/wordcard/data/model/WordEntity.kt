package com.taehee.wordcard.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word")
data class WordEntity(
    @PrimaryKey
    var name: String,
    var time: Long,
)
