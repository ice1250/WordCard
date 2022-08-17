package com.taehee.wordcard.presentation.ui.edit

import com.taehee.wordcard.domain.model.Word
import com.taehee.presentation.R
import com.taehee.presentation.databinding.ViewholderEditBinding
import com.taehee.wordcard.presentation.ui.custom.CustomAdapter
import com.taehee.wordcard.presentation.ui.custom.DiffCallback

class EditRecyclerViewAdapter(
    onDeleteClick: (Word) -> Unit,
) : CustomAdapter<Word, ViewholderEditBinding>(
    R.layout.viewholder_edit, DiffCallback.word(),
    { item, binding ->
        binding.apply {
            textView.text = item.name
            delete.setOnClickListener { onDeleteClick(item) }
        }
    }
)
