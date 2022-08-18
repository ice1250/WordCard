package com.taehee.wordcard.presentation.ui.game

import com.taehee.wordcard.domain.model.Game

data class GameUiState(
    val score: Int = 0,
    val isGameWin: Boolean = false,
    val gameList: List<Game> = listOf(),
)
