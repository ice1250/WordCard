package com.taehee.wordcard.ui.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taehee.domain.model.Game
import com.taehee.domain.model.Word
import com.taehee.wordcard.ui.edit.EditRecyclerViewAdapter
import com.taehee.wordcard.ui.game.GameRecyclerViewAdapter

@BindingAdapter("items")
fun RecyclerView.items(items: List<Word>?) {
    (adapter as? EditRecyclerViewAdapter)?.submitList(items)
    smoothScrollToPosition(adapter!!.itemCount)
}

@BindingAdapter("gameItems")
fun RecyclerView.gameItems(items: List<Game>?) {
    (adapter as? GameRecyclerViewAdapter)?.submitList(items)
}