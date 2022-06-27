package com.taehee.wordcard.ui.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taehee.domain.model.Word

@BindingAdapter("items")
fun RecyclerView.items(items: List<Word>?) {
    (adapter as? ListAdapter<Word, *>)?.submitList(items)
}