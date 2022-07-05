package com.taehee.wordcard.ui.game

import com.taehee.domain.model.Game
import com.taehee.wordcard.R
import com.taehee.wordcard.databinding.ViewholderGameBinding
import com.taehee.wordcard.ui.custom.CustomAdapter
import com.taehee.wordcard.ui.custom.DiffCallback

class GameRecyclerViewAdapter : CustomAdapter<Game, ViewholderGameBinding>(
    R.layout.viewholder_game, DiffCallback.getGame(),
    bind = { item, binding ->
        binding.textView.text = item.name
    }
)