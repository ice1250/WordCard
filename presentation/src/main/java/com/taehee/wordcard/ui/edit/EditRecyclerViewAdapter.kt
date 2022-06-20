package com.taehee.wordcard.ui.edit

import com.taehee.domain.model.Word
import com.taehee.wordcard.R
import com.taehee.wordcard.databinding.ViewholderEditBinding
import com.taehee.wordcard.ui.adapter.CustomAdapter

class EditRecyclerViewAdapter(
    onClick: (Word) -> Unit,
    onDeleteClick: (Word) -> Unit,
) : CustomAdapter<Word, ViewholderEditBinding>(
    R.layout.viewholder_edit,
    Word.DIFF_CALLBACK,
    bind = { item, binding ->
        binding.textView.text = item.name
        binding.delete.setOnClickListener {
            onDeleteClick(item)
        }
        binding.root.setOnClickListener {
            onClick(item)
        }
    }
)
