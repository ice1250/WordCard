package com.taehee.wordcard.ui.edit

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taehee.domain.model.Word

object EditBindingAdapter {

    @BindingAdapter("items", "callback")
    @JvmStatic
    fun bindWords(recyclerView: RecyclerView, items: List<Word>?, listener: OnEditClickListener) {
        if (recyclerView.adapter == null) {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = EditRecyclerViewAdapter(listener)
        }
        val adapter = recyclerView.adapter as EditRecyclerViewAdapter
        if (items != null) {
            adapter.setItems(items)
        } else {
            adapter.setItems(ArrayList())
        }
    }
}