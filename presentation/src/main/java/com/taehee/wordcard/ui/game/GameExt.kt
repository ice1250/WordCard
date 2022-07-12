package com.taehee.wordcard.ui.game

import com.taehee.domain.model.Game

fun List<Game>.flipList(): List<Game> {
    return filter { it.state == Game.GameState.FLIP }
}

fun List<Game>.isSame(): Boolean {
    for (i in 1 until size) {
        if (get(0).name != get(i).name) return false
    }
    return true
}

fun List<Game>.update(game: Game): MutableList<Game> =
    toMutableList().apply { set(game.num, game) }