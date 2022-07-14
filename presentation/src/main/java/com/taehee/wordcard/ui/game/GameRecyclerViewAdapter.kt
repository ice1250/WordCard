package com.taehee.wordcard.ui.game

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import com.taehee.domain.model.Game
import com.taehee.wordcard.R
import com.taehee.wordcard.databinding.ViewholderGameBinding
import com.taehee.wordcard.ui.custom.CustomAdapter
import com.taehee.wordcard.ui.custom.DiffCallback

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

        binding.root.setOnClickListener {
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