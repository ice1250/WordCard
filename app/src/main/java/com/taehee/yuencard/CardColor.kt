package com.taehee.yuencard

object CardColor {
    private val cardColors =
        mutableListOf(
            "#ffc0cb",
            "#ffb6c1",
            "#db7093",
            "#ff1493",
            "#ff69b4",
        )

    fun getCardColors(): String {
        return cardColors[(0 until cardColors.size).random()]
    }
}