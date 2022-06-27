package com.taehee.wordcard.ui.game

import androidx.recyclerview.widget.DiffUtil
import com.taehee.domain.model.Game
import com.taehee.wordcard.R
import com.taehee.wordcard.databinding.ViewholderGameBinding
import com.taehee.wordcard.ui.adapter.CustomAdapter

class GameRecyclerViewAdapter : CustomAdapter<Game, ViewholderGameBinding>(
    R.layout.viewholder_game,
    object : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.name == newItem.name/* && oldItem. == newItem.time*/
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem == newItem
        }
    },
    bind = { item, binding ->
        binding.textView.text = item.name
    }
) {
//    binding.root.minimumHeight = parent.measuredHeight / 4
}