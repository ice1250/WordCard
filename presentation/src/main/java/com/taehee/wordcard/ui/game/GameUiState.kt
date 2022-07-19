package com.taehee.wordcard.ui.game

import com.taehee.domain.model.Game

data class GameUiState(
    val score: Int = 0,
    val isClickable: Boolean = true,
    val isGameWin: Boolean = false,
    val gameList: List<Game> = listOf(),
)
