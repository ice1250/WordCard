package com.taehee.domain.model

data class Game(
    val name: String,
    val num: Int,
) {
    var state: GameState = GameState.NONE
}

enum class GameState {
    NONE,
    FLIP,
    SUCCESS
}