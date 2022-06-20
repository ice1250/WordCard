package com.taehee.wordcard.ui.edit

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taehee.domain.model.Word
import com.taehee.wordcard.databinding.ViewholderEditBinding
import com.taehee.wordcard.ui.adapter.CustomAdapter

@BindingAdapter("items")
fun RecyclerView.items(items: List<Word>?) {
    (adapter as? ListAdapter<Word, *>)?.submitList(items)
}