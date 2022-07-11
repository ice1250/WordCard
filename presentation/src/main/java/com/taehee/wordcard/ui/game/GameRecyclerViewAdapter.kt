package com.taehee.wordcard.ui.game

import android.graphics.Color
import android.view.View
import com.taehee.domain.model.Card
import com.taehee.domain.model.Game
import com.taehee.domain.model.GameState
import com.taehee.wordcard.R
import com.taehee.wordcard.databinding.ViewholderGameBinding
import com.taehee.wordcard.ui.custom.CustomAdapter
import com.taehee.wordcard.ui.custom.DiffCallback

class GameRecyclerViewAdapter(
    onClick: (Game) -> Unit,
) : CustomAdapter<Game, ViewholderGameBinding>(
    R.layout.viewholder_game, DiffCallback.game(),
    bind = { item, binding ->
        if (item.state == GameState.NONE) {
            binding.textView.visibility = View.GONE
        } else {
            binding.textView.visibility = View.VISIBLE
        }
        binding.textView.text = item.name

        binding.root.setOnClickListener {
            onClick(item)
        }
        if (item.state == GameState.SUCCESS) {
            binding.cardView.setCardBackgroundColor(Color.YELLOW)
        } else {
            binding.cardView.setCardBackgroundColor(Color.WHITE)
        }
    }
)