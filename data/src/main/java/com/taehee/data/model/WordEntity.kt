package com.taehee.data.model

import android.graphics.Color
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "word")
data class WordEntity(
    @PrimaryKey
    var name: String,
    var time: Long,
) {
    @Ignore
    var color: Int = Color.WHITE
}
