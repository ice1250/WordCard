package com.taehee.wordcard.ui.edit

import com.taehee.domain.model.Word
import com.taehee.wordcard.R
import com.taehee.wordcard.databinding.ViewholderEditBinding
import com.taehee.wordcard.ui.custom.CustomAdapter
import com.taehee.wordcard.ui.custom.DiffCallback

class EditRecyclerViewAdapter(
    onClick: (Word) -> Unit,
    onDeleteClick: (Word) -> Unit,
) : CustomAdapter<Word, ViewholderEditBinding>(
    R.layout.viewholder_edit, DiffCallback.word(),
    { item, binding ->
        binding.apply {
            textView.text = item.name
            delete.setOnClickListener { onDeleteClick(item) }
            cardView.setOnClickListener { onClick(item) }
        }
    }
)
