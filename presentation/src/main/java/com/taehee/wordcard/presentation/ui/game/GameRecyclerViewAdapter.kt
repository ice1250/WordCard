package com.taehee.wordcard.presentation.ui.game

import android.view.View
import androidx.core.content.ContextCompat
import com.taehee.wordcard.domain.model.Game
import com.taehee.presentation.R
import com.taehee.presentation.databinding.ViewholderGameBinding
import com.taehee.wordcard.presentation.ui.custom.CustomAdapter
import com.taehee.wordcard.presentation.ui.custom.DiffCallback

class GameRecyclerViewAdapter(
    onClick: (Game) -> Unit,
) : CustomAdapter<Game, ViewholderGameBinding>(
    R.layout.viewholder_game, DiffCallback.game(),
    bind = { item, binding ->
        if (item.state == Game.GameState.NONE) {
            binding.textView.visibility = View.GONE
        } else {
            binding.textView.visibility = View.VISIBLE
        }
        binding.textView.text = item.name

        binding.cardView.setOnClickListener {
            onClick(item)
        }
        if (item.state == Game.GameState.SUCCESS) {
            binding.cardView.setCardBackgroundColor(ContextCompat.getColor(binding.cardView.context,
                R.color.shrine_pink_100))
        } else {
            binding.cardView.setCardBackgroundColor(ContextCompat.getColor(binding.cardView.context,
                R.color.shrine_pink_light))
        }
    }
)