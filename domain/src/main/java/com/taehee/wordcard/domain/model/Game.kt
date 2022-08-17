package com.taehee.wordcard.domain.model

data class Game(
    val name: String,
    val num: Int,
    val state: GameState,
){
    enum class GameState {
        NONE,
        FLIP,
        SUCCESS
    }
}