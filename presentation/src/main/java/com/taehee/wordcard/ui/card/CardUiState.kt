package com.taehee.wordcard.ui.card

import com.taehee.domain.model.Card

data class CardUiState(
    val isFetchingCard: Boolean = false,
    val errorMessage: String = "",
    val card: Card? = null,
)
