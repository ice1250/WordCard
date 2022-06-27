package com.taehee.wordcard.ui.game

import androidx.recyclerview.widget.DiffUtil
import com.taehee.domain.model.Word
import com.taehee.wordcard.R
import com.taehee.wordcard.databinding.ViewholderGameBinding
import com.taehee.wordcard.ui.adapter.CustomAdapter

class GameRecyclerViewAdapter : CustomAdapter<Word, ViewholderGameBinding>(
    R.layout.viewholder_game,
    object : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.name == newItem.name && oldItem.time == newItem.time
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }
    },
    bind = { item, binding ->
        binding.textView.text = item.name
    }
) {
//    binding.root.minimumHeight = parent.measuredHeight / 4
}