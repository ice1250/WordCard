package com.taehee.wordcard.ui.edit

import androidx.recyclerview.widget.DiffUtil
import com.taehee.domain.model.Word
import com.taehee.wordcard.R
import com.taehee.wordcard.databinding.ViewholderEditBinding
import com.taehee.wordcard.ui.adapter.CustomAdapter

class EditRecyclerViewAdapter(
    onClick: (Word) -> Unit,
    onDeleteClick: (Word) -> Unit,
) : CustomAdapter<Word, ViewholderEditBinding>(
    R.layout.viewholder_edit,
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
        binding.delete.setOnClickListener {
            onDeleteClick(item)
        }
        binding.root.setOnClickListener {
            onClick(item)
        }
    }
)
