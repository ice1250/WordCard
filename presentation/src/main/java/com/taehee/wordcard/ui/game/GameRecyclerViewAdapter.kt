package com.taehee.wordcard.ui.game

import com.taehee.domain.model.Word
import com.taehee.wordcard.R
import com.taehee.wordcard.databinding.ViewholderGameBinding
import com.taehee.wordcard.ui.adapter.CustomAdapter

class GameRecyclerViewAdapter : CustomAdapter<Word, ViewholderGameBinding>(
    R.layout.viewholder_game, Word.DIFF_CALLBACK,
    bind = { item, binding ->
        binding.textView.text = item.name
    }
) {
//    binding.root.minimumHeight = parent.measuredHeight / 4
}